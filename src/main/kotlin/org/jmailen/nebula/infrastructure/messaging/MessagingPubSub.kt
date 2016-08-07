package org.jmailen.nebula.infrastructure.messaging

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.jmailen.nebula.infrastructure.messaging.support.TopicTree
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Component
import kotlin.concurrent.thread


@Component
class MessagingPubSub(val mqtt: MqttClient): MqttCallback {
    val logger = getLogger(javaClass)
    val json = jacksonObjectMapper()
    var subscribers = TopicTree()

    init {
        mqtt.setCallback(this)
    }

    fun <T : Any> subscribe(topicFilter: String, type: Class<T>, callback: (T) -> Unit) {
        val notifier = fun(payload: ByteArray) {
            thread {
                callback(json.readValue(payload, type))
            }
        }

        synchronized(subscribers, {
            val newSubscription = subscribers.subscribe(topicFilter, notifier)
            if (newSubscription) mqtt.subscribe(topicFilter)
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
        try {
            subscribers.deliver(topic!!, message!!.payload)
        } catch (t: Throwable) {
            logger.error("Processing message on topic: {}, error: {}", topic, t.message)
        }
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
        logger.debug("Message delivered - token: {}", token?.message?.id.toString())
    }
}
