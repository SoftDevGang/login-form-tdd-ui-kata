import React from "react";

interface props {
    authenticate: (userName: string, password: string) => {
        success: boolean,
        message: string
    };
}

class Login extends React.Component<props> {

    render() {
        return <div>
            <label htmlFor={"1"}>Phone, email or username</label>
            <input id={"1"} type={"text"}/>

            <button onClick={() => this.authenticate()}>
                Login
            </button>

        </div>;
    }

    private authenticate(): void {
        const userName: string = "userNameBob";
        const password: string = "secretPassword";

        this.props.authenticate(userName, password);
    }
}

export default Login;
