package org.jmailen.nebula.service.player

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.fusesource.hawtbuf.Buffer
import org.fusesource.hawtbuf.UTF8Buffer
import org.fusesource.mqtt.client.*
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Service

const val PLAYER_JOIN_TOPIC = "/client/player/join"
val playerSubscriptions = arrayOf(Topic(PLAYER_JOIN_TOPIC, QoS.AT_LEAST_ONCE))

const val PLAYER_ADD_TOPIC = "/server/player/add"

@Service
class PlayerPubSub(val mqtt: MQTT) : ExtendedListener {
    val logger = getLogger(this.javaClass)
    val json = jacksonObjectMapper()

    val pubsub = mqtt.callbackConnection()

    init {
        pubsub.listener(this)

        pubsub.connect(object: Callback<Void> {
            override fun onSuccess(value: Void?) {
                pubsub.subscribe(playerSubscriptions, object: Callback<ByteArray> {
                    override fun onSuccess(value: ByteArray?) {
                        logger.info("Subscribed to topics: {}", playerSubscriptions.map { it.name() })
                    }
                    override fun onFailure(err: Throwable) {
                        logger.error("Error subscribing to topics: {}", err)
                    }
                })
            }
            override fun onFailure(err: Throwable?) {
                logger.error("Failed to connect to broker: {}", err)
            }
        })
    }

    override fun onConnected() {
        logger.info("Connected to broker")
    }

    override fun onDisconnected() {
        logger.info("Connected from broker")
    }

    override fun onPublish(topic: UTF8Buffer, body: Buffer, ack: Runnable) {
        when (topic.toString()) {
            PLAYER_JOIN_TOPIC -> handlePlayerJoin(json.readValue(body.data))
        }
        ack.run()
    }

    override fun onPublish(topic: UTF8Buffer, body: Buffer, ack: Callback<Callback<Void>>) {
        onPublish(topic, body, { ack.onSuccess(null) })
    }

    override fun onFailure(value: Throwable) {
        logger.error("Error ")
    }

    fun handlePlayerJoin(player: Player) {
        logger.info("Player joined: {}", player)
        publish(PLAYER_ADD_TOPIC, player)
    }

    fun publish(topic: String, payload: Any) {
        pubsub.publish(topic, json.writeValueAsBytes(payload), QoS.AT_LEAST_ONCE, false, object: Callback<Void> {
            override fun onSuccess(value: Void?) {}
            override fun onFailure(err: Throwable) {
                logger.error("Error publishing message: topic = {}, error = {}", topic, err)
                logger.trace("Payload was: {}", payload)
            }
        })
    }
}
