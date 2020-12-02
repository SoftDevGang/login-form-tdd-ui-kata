import './App.css';
import Login from "./components/Login";
import logo from './logo.svg';
import React from "react";
import {authenticate, AuthenticateResult} from "./components/Authenticate";

class App extends React.Component<any> {
    render() {
        return <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
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
            <Login failedLogin={false} authenticate={(userName: string, password: string) => {
                authenticate(userName, password).then((result: AuthenticateResult) => {
                    // todo show welcome message
                });

            }}/>

            {this.getWelcomeMessage()}

        </div>
    }

    private getWelcomeMessage(): React.ReactFragment {
        return <></>
        /*
        return <p aria-label={"Welcome message"}>
            Welcome Bob!
        </p>;
         */
    }
}

export default App;
