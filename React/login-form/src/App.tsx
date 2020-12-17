import './App.css';
import Login from "./components/Login";
import React from "react";
import { authenticate, AuthenticateResult } from "./components/Authenticate";

interface State {
    isLoggedIn: boolean;
    loginTries: number;
    lastUserName: string;
}

class App extends React.Component<any, State> {
    constructor(props: Readonly<any>) {
        super(props);
        this.initState();
    }

    private initState() {
        this.state = {
            isLoggedIn: false,
            loginTries: 0,
            lastUserName: ""
        };
    }

    public render() {
        return <div className="App">
            <header className="App-header">
                <p>
                    Welcome to Clean Code Center!
                </p>
            </header>

            {this.loginDialogComponent()}
            {this.welcomeMessageComponent()}

        </div>
    }

    private loginDialogComponent(): React.ReactFragment {
        const isLoggedIn: boolean = this.state.isLoggedIn;
        if (isLoggedIn) {
            return <></>;
        }

        const loginTries: number = this.state.loginTries;
        return <Login
            failedLogin={loginTries > 0}
            authenticate={(userName, password) => this.tryLogin(userName, password)} />;
    }

    private tryLogin(userName: string, password: string) {
        authenticate(userName, password).
            then(authenticationResult => this.updateLoginStats(authenticationResult, userName));
    }

    private updateLoginStats(authenticationResult: AuthenticateResult, userName: string) {
        const loginTries: number = this.state.loginTries;
        this.setState({
            isLoggedIn: authenticationResult.success,
            loginTries: loginTries + 1,
            lastUserName: userName
        });
    }

    private welcomeMessageComponent(): React.ReactFragment {
        const loggedIn: boolean = this.state.isLoggedIn;
        const userName: string = this.state.lastUserName;

        if (!loggedIn) {
            return <></>;
        }

        return <p aria-label={"Welcome message"}>
            Welcome {userName}!
        </p>;
    }
}

export default App;
