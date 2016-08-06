package org.jmailen.nebula.infrastructure.messaging

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Component
import java.util.*
import kotlin.concurrent.thread


@Component
class MessagingPubSub(val mqtt: MqttClient): MqttCallback {
    val logger = getLogger(javaClass)
    val json = jacksonObjectMapper()

    val subscribers = HashMap<String, MutableList<(ByteArray) -> Unit>>()

    init {
        mqtt.setCallback(this)
    }

    fun <T : Any> subscribe(topic: String, type: Class<T>, callback: (T) -> Unit) {
        val notifier = fun(payload: ByteArray) = callback(json.readValue(payload, type))

        synchronized(subscribers, {
            if (subscribers[topic] != null) {
                subscribers[topic]?.add(notifier)
            } else {
                subscribers[topic] = mutableListOf(notifier)
                mqtt.subscribe(topic)
            }
        })
    }

    fun publish(topic: String, messageData: Any) {
        // TODO: use fixed thread pool
        thread {
            mqtt.publish(topic, json.writeValueAsBytes(messageData), 1, false)
        }
    }

    override fun connectionLost(cause: Throwable?) {
        logger.warn("Connection lost: {}", cause?.message)
    }

    override fun messageArrived(topic: String?, message: MqttMessage?) {
        // TODO: route to topics with wildcards
        subscribers[topic]?.forEach { notifier ->
            thread {
                try {
                    notifier(message!!.payload)
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
