import React from 'react';
import {render, screen} from '@testing-library/react';
import Login from "./Login";

test('See the login dialog', async () => {
    render(<Login/>);

    const actual = screen.getByLabelText("Phone, email or username");

    expect(actual).toBeInTheDocument();
});
