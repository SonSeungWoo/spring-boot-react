import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {

    state = {};

    componentDidMount() {
        console.log('111')
        // setInterval(setIntervalthis.hello, 250);
    }



    hello = () => {
    console.log('111')
    fetch('/hello')
.then(response => response.text())
.then(message => {
    console.log('message', message)
    this.setState({message: message});
});
};

render() {
    return (
        <div className="App">
        <header className="App-header">
        <img src={logo} className="App-logo" alt="logo"/>
        <h1 className="App-title">{this.state.message}</h1>
        </header>
        <button className="App-intro" onClick={this.hello}>
        <h1>클릭하세요.</h1>
        </button>
        </div>
);
}
}
export default App;
