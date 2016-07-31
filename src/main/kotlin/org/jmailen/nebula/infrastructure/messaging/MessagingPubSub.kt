package org.jmailen.nebula.infrastructure.messaging

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*
import kotlin.concurrent.thread

@Component
class MessagingPubSub(val mqtt: MqttClient): MqttCallback {
    val logger = LoggerFactory.getLogger(javaClass)
    val subscribers = HashMap<String, MutableList<(ByteArray) -> Unit>>()

    init {
        mqtt.setCallback(this)
    }

    fun subscribe(topic: String, callback: (ByteArray) -> Unit) {
        synchronized(subscribers, {
            if (subscribers[topic] != null) {
                subscribers[topic]?.add(callback)
            } else {
                subscribers[topic] = mutableListOf(callback)
                mqtt.subscribe(topic)
            }
        })
    }

    fun publish(topic: String, messageData: ByteArray) {
        // TODO: use fixed thread pool
        thread {
            mqtt.publish(topic, messageData, 1, false)
        }
    }

    override fun connectionLost(cause: Throwable?) {
        logger.warn("Connection lost: {}", cause?.message)
    }

    override fun messageArrived(topic: String?, message: MqttMessage?) {
        // TODO: route to topics with wildcards
        subscribers[topic]?.forEach { callback ->
            thread {
                try {
                    callback(message!!.payload)
                } catch (t: Throwable) {
                    logger.error("Processing message for topic: {}, error: {}", topic, t.message)
                }
            }
        }
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
        logger.debug("Message delivered - token: {}", token?.message?.id.toString())
    }
}