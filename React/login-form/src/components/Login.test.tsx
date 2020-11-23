import React from 'react';
import {render, screen} from '@testing-library/react';
import Login from "./Login";

test('See the login dialog', async () => {
    render(<Login authenticate={(userName, password) => {
        return {success: false, message: ""}
    }}/>);

    const actual = screen.getByLabelText("Phone, email or username");

    expect(actual).toBeInTheDocument();
});

test.skip('See the login dialog', async () => {
    render(<Login authenticate={(userName, password) => {
        return {success: false, message: ""}
    }}/>);

    const actual = screen.getByLabelText("Phone, email or username");

    expect(actual).toBeInTheDocument();
});
