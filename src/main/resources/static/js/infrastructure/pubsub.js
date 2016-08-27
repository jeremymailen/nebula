import uuid from 'node-uuid';
import Paho from 'paho-mqtt';

var mqtt = new Paho.MQTT.Client(window.location.hostname, 8004, '/', 'webapp.' + uuid.v4());

mqtt.connect({
    userName: 'mqtt',
    password: 'secret',
    onSuccess: onConnect,
    onFailure: function(resp) { console.error('MQTT connect failed: ', resp); }
});

function onConnect() {
    console.log('MQTT connected: ');
    mqtt.subscribe('server/#');
}

mqtt.onMessageArrived = receive;

function receive(message) {
    console.log('MQTT message received: topic =', message.destinationName, ' payload =', message.payloadString);
}

function send(payload, topic) {
    var msg = new Paho.MQTT.Message(JSON.stringify(payload));
    msg.destinationName = topic;
    msg.qos = 1;
    mqtt.send(msg);
}

export { send };
