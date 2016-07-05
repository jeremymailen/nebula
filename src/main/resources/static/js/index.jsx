// MQTT
var mqtt = new Paho.MQTT.Client(window.location.hostname, 8004, '/', 'webapp.' + uuid.v4());

mqtt.connect({
    userName: 'mqtt',
    password: 'secret',
    onSuccess: onConnect,
    onFailure: function(resp) { console.error('MQTT connect failed: ', resp); }
});

mqtt.onMessageArrived = receive;

function onConnect() {
    console.log('MQTT connected: ');
    mqtt.subscribe('server/#');
}

function receive(message) {
    console.log('MQTT message received: topic =', message.destinationName, ' payload =', message.payloadString);
}

function send(payload, topic) {
    var msg = new Paho.MQTT.Message(JSON.stringify(payload));
    msg.destinationName = topic;
    msg.qos = 1;
    mqtt.send(msg);
}
// MQTT


var PlayerLogin = React.createClass({
    getInitialState: function() {
        return {
            player: {
                id: null,
                name: null,
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
