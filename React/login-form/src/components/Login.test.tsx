import React from 'react';
import {render, screen} from '@testing-library/react';
import Login from "./Login";
import userEvent from "@testing-library/user-event";

test('See the login dialog', async () => {
    render(<Login authenticate={(userName, password) => {
        return {success: false, message: ""}
    }}/>);

    const actual = screen.getByLabelText("Phone, email or username");

    expect(actual).toBeInTheDocument();
});

test('Call authenticate on button click', async () => {
    const authenticate = jest.fn();
    render(<Login authenticate={authenticate}/>);

    const userName = screen.getByLabelText("Phone, email or username");
    userEvent.type(userName, "userNameBob");

    const loginButton: HTMLElement = screen.getByText("Login");
    await userEvent.click(loginButton);

    expect(authenticate).toHaveBeenNthCalledWith(1, "userNameBob", "secretPassword");
});
