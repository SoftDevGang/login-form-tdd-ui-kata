import React from 'react';
import {render, screen} from '@testing-library/react';
import App from './App';

test('Clean code center should be in the page title', async () => {
    render(<App/>);

    const actual = screen.getByText("Welcome to Clean Code Center!")

    expect(actual).toBeInTheDocument();
});
