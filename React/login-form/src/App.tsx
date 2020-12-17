import './App.css';
import Login from "./components/Login";
import logo from './logo.svg';
import React from "react";
import { authenticate, AuthenticateResult } from "./components/Authenticate";

interface State {
    loggedIn: boolean;
    loginTries: number;
    userName: string;
}

class App extends React.Component<any, State> {
    constructor(props: Readonly<any>) {
        super(props);
        this.state = {
            loggedIn: false,
            loginTries: 0,
            userName: ""
        };
    }

    render() {
        return <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <p>
                    Welcome to Clean Code Center!
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
            </header>

            {this.loginDialog()}
            {this.getWelcomeMessage()}

        </div>
    }

    private loginDialog(): React.ReactFragment {
        const loggedIn: boolean = this.state.loggedIn;
        const loginTries: number = this.state.loginTries;

        if (!loggedIn) {
            return <Login failedLogin={loginTries > 0} authenticate={(userName: string, password: string) => {
                authenticate(userName, password).then((result: AuthenticateResult) => {
                    this.setState({
                        loggedIn: result.success,
                        loginTries: loginTries + 1,
                        userName
                    });
                });
            }} />;
        } else {
            return <></>;
        }
    }

    private getWelcomeMessage(): React.ReactFragment {
        const loggedIn: boolean = this.state.loggedIn;
        const userName: string = this.state.userName;

        if (loggedIn) {
            return <p aria-label={"Welcome message"}>
                Welcome {userName}!
            </p>;
        } else {
            return <></>;
        }
    }
}

export default App;
