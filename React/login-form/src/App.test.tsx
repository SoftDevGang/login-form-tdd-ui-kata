import { render, screen } from '@testing-library/react';
import App from './App';
import userEvent from "@testing-library/user-event";
import { rest } from 'msw'
import { setupServer } from 'msw/node';

let statusCode = 200;
let body = { success: false, message: "" };

const server = setupServer(
    rest.post('/api/v1/login', (req, res, ctx) => {
        return res(
            ctx.status(statusCode),
            ctx.set('Content-Type', 'application/json'),
            ctx.json(body)
        );
    }),
);

afterEach(() => server.resetHandlers());
beforeAll(() => server.listen());
afterAll(() => server.close());

// 1. make sure we see the app at all
test('See "Clean Code Center"', () => {
    render(<App />);

    const element = screen.getByText("Welcome to Clean Code Center!");

    expect(element).toBeInTheDocument();
});

// 1.2. make sure we see the app at all
test('See the login dialog', () => {
    render(<App />);

    expectLoginDialogFields();
});

function expectLoginDialogFields() {
    const element = screen.getByLabelText("Phone, email or username");
    expect(element).toBeInTheDocument();
}

// 4. wire the authentication callback
test('See welcome message on a successful login', async () => {
    body = { success: true, message: "" };
    render(<App />);

    typeUserNameAndPassword("Bill", "secretPassword");
    await clickLoginButton();

    // next component is open
    const welcome = screen.getByLabelText("Welcome message");
    expect(welcome).toBeInTheDocument(); // generic for just checking existence
    expect(welcome).toHaveTextContent("Welcome Bill!");

    // reuse test - do not show Login component on success
    expect(screen.queryByLabelText("Phone, email or username")).toBeNull();
});


function typeUserNameAndPassword(userName: string, password: string) {
    const userNameField = screen.getByLabelText("Phone, email or username");
    userEvent.type(userNameField, userName);
    const passwordField = screen.getByLabelText("Password");
    userEvent.type(passwordField, password);
}

async function clickLoginButton() {
    const loginButton: HTMLElement = screen.getByText("Login");
    userEvent.click(loginButton);
    await sleep(50);
}

function sleep(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// 4.1. authentication callback failure
test('See error dialog on a failed login', async () => {
    body = { success: false, message: "" };
    render(<App />);

    // no error dialog at startup
    expect(screen.queryByLabelText("Failed login")).toBeNull();

    typeUserNameAndPassword("Bob", "secretPassword");
    await clickLoginButton();

    // login is still here
    expectLoginDialogFields();

    // and it displays error message
    const errorMessage = screen.getByLabelText("Failed login");
    expect(errorMessage).toBeInTheDocument(); // generic for just checking existence
});
