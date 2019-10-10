import React from 'react';

export const cleaner = (dirty) => {
    const cleaned = dirty;

    return cleaned;
};

export class Login extends React.Component {

    validateInput() {
        this.props.onValidate('userName', 'password');
        console.log("I was called")
    }

    render() {
        return (
            <div>
                <input type={"text"}
                       name={"userName"}
                       placeholder={"user name"}
                       required={"required"}>
                </input>
                <label htmlFor={"userName"}>
                    User name:
                </label>

                <input type={"text"}
                       name={"password"}
                       placeholder={"password"}
                       required={"required"}>
                </input>
                <label htmlFor={"password"}>
                    Password:
                </label>

                <input type={"button"}
                       name={"submit"}
                       value={"Sign in"}
                       onClick={() => this.validateInput()}>
                </input>
            </div>
        );
    }
}


const DefaultFixME = props => <Login onValidate={() => console.log("Fix me")} />;

export default DefaultFixME