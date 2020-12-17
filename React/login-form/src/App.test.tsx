import { render, screen } from '@testing-library/react';
import App from './App';
import userEvent from "@testing-library/user-event";
import { rest } from 'msw'
import { setupServer } from 'msw/node';

const server = setupServer(
    rest.post('/api/v1/login', (req, res, ctx) => {
        return res(
            ctx.status(200),
            ctx.set('Content-Type', 'application/json'),
            ctx.json({ success: true, message: "" })
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

    const element = screen.getByLabelText("Phone, email or username");

    expect(element).toBeInTheDocument();
});

// TODO test case: the error should not be displayed when we see the login the first time

// 4. wire the authentication callback
test('See welcome message on a successful login', async () => {
    render(<App />);
    const userName = screen.getByLabelText("Phone, email or username");
    userEvent.type(userName, "Bob");
    const password = screen.getByLabelText("Password");
    userEvent.type(password, "secretPassword");

    const loginButton: HTMLElement = screen.getByText("Login");
    userEvent.click(loginButton);
    await sleep(50);

    const welcome = screen.getByLabelText("Welcome message");
    expect(welcome).toHaveTextContent("Welcome Bob!");

    // reuse test - do not show Login component on success
    expect(screen.queryByLabelText("Phone, email or username")).toBeNull();
});

function sleep(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

// - see the username in welcome message - variant
// - Show error message at login failure
