import React from 'react';
import {cleaner, Login} from './Login';
import renderer from 'react-test-renderer';

import {fireEvent, render} from '@testing-library/react'

describe('Login', () => {


    describe('Rendering', () => {
        test('login should render properly', () => {

            const component = renderer.create(
                <Login/>,
            );
            let tree = component.toJSON();
            expect(tree).toMatchSnapshot();

        });

    });

    describe('Submitting', () => {
        test('Field should not be empty', () => {

            const validateFn = jest.fn();

            const {getByText} = render(<Login onValidate={validateFn}/>);
            fireEvent.click(getByText('Sign in'));

            expect(validateFn).toHaveBeenCalled();


        });

    });

    test('Clean a string', () => {
        expect(cleaner("foo")).toEqual("foo");
    });

    test('login should', () => {
        /*
                const component = renderer.create(
                    <Link page="http://www.facebook.com">Facebook</Link>,
                );
                let tree = component.toJSON();
                expect(tree).toMatchSnapshot();

                // manually trigger the callback
                tree.props.onMouseEnter();
                // re-rendering
                tree = component.toJSON();
                expect(tree).toMatchSnapshot();

                // manually trigger the callback
                tree.props.onMouseLeave();
                // re-rendering
                tree = component.toJSON();
                expect(tree).toMatchSnapshot();

         */
    });


});


