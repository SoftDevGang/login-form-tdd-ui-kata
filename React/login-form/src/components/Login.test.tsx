import {render, screen} from '@testing-library/react';
import userEvent from "@testing-library/user-event";
import Login from "./Login";

test('See the login dialog with two input fields', async () => {
    const authenticate = jest.fn();
    render(<Login authenticate={authenticate}/>);

    const actualUsername = screen.getByLabelText("Phone, email or username");
    const actualPassword = screen.getByLabelText("Password");

    expect(actualUsername).toBeInTheDocument();
    expect(actualPassword).toBeInTheDocument();
});

test('The password field should display stars when typing in it', async () => {
    const authenticate = jest.fn();
    render(<Login authenticate={authenticate}/>);

    const actualPassword = screen.getByLabelText("Password");

    expect(actualPassword).toHaveAttribute("type", "password");
});

test('Call authenticate on button click', async () => {
    const authenticate = jest.fn();
    render(<Login authenticate={authenticate}/>);

    const userName = screen.getByLabelText("Phone, email or username");
    userEvent.type(userName, "userNameBob");
    const password = screen.getByLabelText("Password");
    userEvent.type(password, "secretPassword");

    const loginButton: HTMLElement = screen.getByText("Login");
    await userEvent.click(loginButton);

    expect(authenticate).toHaveBeenNthCalledWith(1, "userNameBob", "secretPassword");
});
