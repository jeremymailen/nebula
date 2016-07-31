package org.jmailen.nebula.infrastructure.messaging.support

import org.eclipse.paho.client.mqttv3.MqttConnectOptions

fun MqttConnectOptions.withCredentials(username: String, password: String): MqttConnectOptions {
    this.userName = username
    this.password = password.toCharArray()
    return this
}
