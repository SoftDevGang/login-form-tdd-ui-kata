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

test('Clean code center should be in the page title', async () => {
    render(<App />);

    const actual = screen.getByText("Welcome to Clean Code Center!");

    expect(actual).toBeInTheDocument();
});

test('See the login dialog', async () => {
    render(<App />);

    const actual = screen.getByLabelText("Phone, email or username");

    expect(actual).toBeInTheDocument();
});

test('Should display a welcome message on a successful login', async () => {
    render(<App />);
    const userName = screen.getByLabelText("Phone, email or username");
    userEvent.type(userName, "Bob");
    const password = screen.getByLabelText("Password");
    userEvent.type(password, "secretPassword");

    const loginButton: HTMLElement = screen.getByText("Login");
    await userEvent.click(loginButton);

    await sleep(50);

    const actual = screen.getByLabelText("Welcome message");
    expect(actual).toHaveTextContent("Welcome Bob!");
});

function sleep(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
