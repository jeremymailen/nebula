import React from 'react';
import ReactDOM from 'react-dom';
import uuid from 'node-uuid';
import { send } from 'js/infrastructure/pubsub';

var PlayerLogin = React.createClass({
    getInitialState: function() {
        return {
            player: {
                id: null,
                name: "",
            }
        };
    },
    handleNameChange: function(e) {
        this.setState({player: {name: e.target.value}});
    },
    handleLogin: function(e) {
        e.preventDefault();
        var player = {
            id: uuid.v4(),
            name: this.state.player.name.trim()
        };
        this.setState({player: player});
        send(player, 'client/player/join');
    },
    render: function() {
        return (
            <form className="playerLogin" onSubmit={this.handleLogin}>
                <input type="text" placeholder="Player Name" value={this.state.player.name} onChange={this.handleNameChange} />
                <input type="submit" value="Join" />
            </form>
        );
    }
});

var AppContainer = React.createClass({
    render: function() {
        return (
            <div className="app">
                <PlayerLogin />
            </div>
        );
    }
});

ReactDOM.render(
  <AppContainer />,
  document.getElementById('app')
);
