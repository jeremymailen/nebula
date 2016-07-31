package org.jmailen.nebula.infrastructure.messaging

import io.moquette.BrokerConstants
import io.moquette.server.Server
import io.moquette.server.config.MemoryConfig
import io.moquette.spi.security.IAuthenticator
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.jmailen.nebula.infrastructure.messaging.support.withCredentials
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.annotation.PreDestroy

val MQTT_USERNAME = "mqtt"
val MQTT_PASSWORD = "secret"
val MQTT_PORT = 8003
val MQTT_WS_PORT = 8004

// Extending standard Java Properties with sugar for setting properties using Pair and its infix 'to' method
operator fun Properties.plusAssign(prop: Pair<String, String>) {
    this.setProperty(prop.first, prop.second)
}

class FixedBrokerAuthenticator: IAuthenticator {
    override fun checkValid(user: String, password: ByteArray) = (user == MQTT_USERNAME && String(password) == MQTT_PASSWORD)
}

@Configuration
open class MessagingConfig() {

    @Bean
    open fun mqttBroker(): Server {
        val broker = Server()

        val brokerConfig = Properties()
        brokerConfig += BrokerConstants.HOST_PROPERTY_NAME to "0.0.0.0"
        brokerConfig += BrokerConstants.PORT_PROPERTY_NAME to MQTT_PORT.toString()
        brokerConfig += BrokerConstants.WEB_SOCKET_PORT_PROPERTY_NAME to MQTT_WS_PORT.toString()
        brokerConfig += BrokerConstants.AUTHENTICATOR_CLASS_NAME to FixedBrokerAuthenticator::class.java.name

        broker.startServer(MemoryConfig(brokerConfig))
        return broker
    }

    @Bean
    @Autowired
    open fun mqttClient(broker: Server): MqttClient {
        val mqtt = MqttClient("tcp://localhost:$MQTT_PORT", MqttClient.generateClientId())
        mqtt.connect(MqttConnectOptions().withCredentials(MQTT_USERNAME, MQTT_PASSWORD))
        return mqtt
    }

    @PreDestroy
    open fun shutdown() {
        mqttBroker().stopServer()
    }
}