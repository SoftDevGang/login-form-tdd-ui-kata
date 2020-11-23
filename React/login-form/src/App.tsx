import React from 'react';
import logo from './logo.svg';
import './App.css';

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

          <label htmlFor={"1"}>Phone, email or username</label>
          <input id={"1"} type={"text"}/>
      </div>
  );
}

export default App;
