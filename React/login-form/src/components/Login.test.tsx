import { render, screen } from '@testing-library/react';
import userEvent from "@testing-library/user-event";
import Login from "./Login";

// 2. drive the UI of the login dialog
test('See the login dialog with two input fields', () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={false} authenticate={authenticate} />);

    const userName = screen.getByLabelText("Phone, email or username");
    const password = screen.getByLabelText("Password");

    expect(userName).toBeInTheDocument();
    expect(password).toBeInTheDocument();
});

// 2.2
test('The password field displays stars when typing in it', () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={false} authenticate={authenticate} />);

    const password = screen.getByLabelText("Password");

    expect(password).toHaveAttribute("type", "password");
});

// 2.3 drive the logic
test('Call authenticate on button click', () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={false} authenticate={authenticate} />);
    const userName = screen.getByLabelText("Phone, email or username");
    userEvent.type(userName, "userNameBob");
    const password = screen.getByLabelText("Password");
    userEvent.type(password, "secretPassword");

    const loginButton: HTMLElement = screen.getByText("Login");
    userEvent.click(loginButton);

    expect(authenticate).toHaveBeenNthCalledWith(1, "userNameBob", "secretPassword");
});

// 2.4 interaction with the result of callback
test('Display error message on failed login', () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={true} authenticate={authenticate} />);

    const errorMessage = screen.getByLabelText("Failed login");

    expect(errorMessage).toHaveTextContent("You are not logged in");
    // TODO check that we can see a proper error css
    // expect(errorMessage).toHaveAttribute("class", "foo");
});

// 2.5
test('Display no error message on first login', () => {
    const authenticate = jest.fn();
    render(<Login failedLogin={false} authenticate={authenticate} />);

    const errorMessage = screen.findByLabelText("Failed login").
        then(_ => { expect(true).toBeFalsy(); });
        // weird: returns a Promise with {} inside?
});
