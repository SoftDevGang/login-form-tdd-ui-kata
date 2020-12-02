import React from "react";
import {AuthenticateResult} from "./Authenticate";

interface Props {
    authenticate: (userName: string, password: string) => AuthenticateResult;
}

interface State {
    userName: string;
    password: string;
}

class Login extends React.Component<Props, State> {

    constructor(props: Readonly<Props>) {
        super(props);
        this.state = {
            userName: "",
            password: ""
        };
    }

    public render() {
        const userName = this.state.userName;
        const password = this.state.password;

        return <div>
            <label htmlFor={"userName"}>Phone, email or username</label>
            <input id={"userName"}
                   type={"text"}
                   value={userName}
                   onChange={(event) => this.setUserName(event)}
            />
            <label htmlFor={"password"}>Password</label>
            <input id={"password"}
                   type={"text"}
                   value={password}
                   onChange={(event) => this.setPassword(event)}
            />

            <button onClick={() => this.authenticate()}>
                Login
            </button>

        </div>;
    }

    private authenticate(): void {
        const userName: string = this.state.userName;
        const password: string = this.state.password;

        this.props.authenticate(userName, password);
    }

    private setUserName(event: React.ChangeEvent<HTMLInputElement>) {
        const userName: string = event.currentTarget.value;
        this.setState({userName});
    }

    private setPassword(event: React.ChangeEvent<HTMLInputElement>) {
        const password: string = event.currentTarget.value;
        this.setState({password: password});
    }
}

export default Login;