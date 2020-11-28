// import React from 'react';
import './App.css';
import Login from "./components/Login";
import logo from './logo.svg';

function App() {
    return (
        <div className="App">
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
            <Login authenticate={(userName: string, password: string) => {
                return { success: false, message: "" };
            }} />
        </div>
    );
}

export default App;
