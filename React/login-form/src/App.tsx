import React from 'react';
import logo from './logo.svg';
import './App.css';
import Login from "./components/Login";

function App() {
    return (
        <div className="App">
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
            <Login authenticate={(userName, password) => {
                return {success: false, message: ""}
            }}/>
        </div>
    );
}

export default App;
