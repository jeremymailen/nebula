package org.jmailen.nebula.infrastructure.messaging

import io.moquette.BrokerConstants
import io.moquette.server.Server
import io.moquette.server.config.MemoryConfig
import io.moquette.spi.security.IAuthenticator
import org.fusesource.mqtt.client.MQTT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.annotation.PreDestroy

val MQTT_USERNAME = "mqtt"
val MQTT_PASSWORD = "secret"

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
        brokerConfig += BrokerConstants.PORT_PROPERTY_NAME to "8003"
        brokerConfig += BrokerConstants.WEB_SOCKET_PORT_PROPERTY_NAME to "8004"
        brokerConfig += BrokerConstants.AUTHENTICATOR_CLASS_NAME to FixedBrokerAuthenticator::class.java.name

        broker.startServer(MemoryConfig(brokerConfig))
        return broker
    }

    @Bean
    @Autowired
    open fun mqttClient(broker: Server): MQTT {
        val client = MQTT()

        client.setHost("localhost", 8003)
        client.setUserName(MQTT_USERNAME)
        client.setPassword(MQTT_PASSWORD)

        return client
    }

    @PreDestroy
    open fun shutdown() {
        mqttBroker().stopServer()
    }
}