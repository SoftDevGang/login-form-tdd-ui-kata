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
    /*
    let authenticate = (userName, password) => {

        return {success: false, message: ""}
    };
     */

    const authenticate = jest.fn();
    render(<Login authenticate={authenticate}/>);

    // todo enter username, password and click login
    // const actual = screen.getByLabelText("Phone, email or username");

    const loginButton: HTMLElement = await screen.getByText("Login");
    await userEvent.click(loginButton);

    expect(authenticate).toHaveBeenNthCalledWith(1, "userNameBob", "secretPassword");
});
