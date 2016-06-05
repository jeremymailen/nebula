package org.jmailen.nebula.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.fusesource.hawtbuf.Buffer
import org.fusesource.mqtt.client.MQTT
import org.fusesource.mqtt.client.QoS
import org.fusesource.mqtt.client.Topic
import org.hamcrest.CoreMatchers.equalTo
import org.jmailen.nebula.Service
import org.jmailen.nebula.service.health.HEALTH_API_PATH
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus.OK
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals

fun TestRestTemplate.get(pathTemplate: String) = getForEntity(pathTemplate, Map::class.java)

data class TestMessage(var id: Int, var text: String)

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Service::class), webEnvironment = RANDOM_PORT)
class ServiceIntegrationTest() {

    @Autowired lateinit var rest: TestRestTemplate;

    fun TestRestTemplate.getHealth() = get(HEALTH_API_PATH)

    @Test fun healthApi() {
        val result = rest.getHealth()

        assertThat(result.statusCode, equalTo(OK))
        assertEquals("Nebula", result.body["name"])
        assertEquals("unknown", result.body["version"])
    }

    @Autowired lateinit var mqtt: MQTT;

    @Test fun mqttMessaging() {
        val json = jacksonObjectMapper()
        val testTopics = arrayOf(Topic("/test", QoS.AT_LEAST_ONCE))
        val publisher = mqtt.blockingConnection()
        val subscriber = mqtt.blockingConnection()

        publisher.connect()
        subscriber.connect()
        subscriber.subscribe(testTopics)

        val sentItem = TestMessage(1, "Hello")
        val sentData = json.writeValueAsBytes(sentItem)
        publisher.publish(testTopics.first().name(), Buffer(sentData), QoS.AT_LEAST_ONCE, false)

        val receivedMessage = subscriber.receive()
        assertThat(receivedMessage.topic, equalTo("/test"))
        val receivedItem: TestMessage = json.readValue(receivedMessage.payload)
        assertThat(receivedItem, equalTo(sentItem))

        publisher.disconnect()
        subscriber.disconnect()
    }
}
