import {render, screen} from '@testing-library/react';
import userEvent from "@testing-library/user-event";
import Login from "./Login";

test('See the login dialog with two input fields', async () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={false} authenticate={authenticate}/>);

    const actualUsername = screen.getByLabelText("Phone, email or username");
    const actualPassword = screen.getByLabelText("Password");

    expect(actualUsername).toBeInTheDocument();
    expect(actualPassword).toBeInTheDocument();
});

test('The password field should display stars when typing in it', async () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={false} authenticate={authenticate}/>);

    const actualPassword = screen.getByLabelText("Password");

    expect(actualPassword).toHaveAttribute("type", "password");
});

test('Call authenticate on button click', async () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={false} authenticate={authenticate}/>);

    const userName = screen.getByLabelText("Phone, email or username");
    userEvent.type(userName, "userNameBob");
    const password = screen.getByLabelText("Password");
    userEvent.type(password, "secretPassword");

    const loginButton: HTMLElement = screen.getByText("Login");
    await userEvent.click(loginButton);

    expect(authenticate).toHaveBeenNthCalledWith(1, "userNameBob", "secretPassword");
});

// todo the error should not be displayed when we see the login the first time

test('Should display error message on failed login', async () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={true} authenticate={authenticate}/>);

    const errorMessage = screen.getByLabelText("Failed login");

    expect(errorMessage).toHaveTextContent("You are not logged in");
    // todo check that we can see a proper error css
});
