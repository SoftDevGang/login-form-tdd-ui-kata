import React from "react";
import {AuthenticateResult} from "./Authenticate";

interface props {
    authenticate: (userName: string, password: string) => AuthenticateResult;
}

interface state {
    userName: string
}

class Login extends React.Component<props, state> {

    constructor(props: Readonly<props>) {
        super(props);
        this.state = {
            userName: ""
        }
    }

    render() {
        const userName = this.state.userName;

        return <div>
            <label htmlFor={"1"}>Phone, email or username</label>
            <input id={"1"}
                   type={"text"}
                   value={userName}
                   onChange={(event) => this.setUserName(event)}
            />

            <button onClick={() => this.authenticate()}>
                Login
            </button>

        </div>;
    }

    private authenticate(): void {
        const userName: string = this.state.userName;
        const password: string = "secretPassword";

        this.props.authenticate(userName, password);
    }

    private setUserName(event: React.ChangeEvent<HTMLInputElement>) {
        const userName: string = event.currentTarget.value;
        this.setState({userName: userName});
    }
}

export default Login;
