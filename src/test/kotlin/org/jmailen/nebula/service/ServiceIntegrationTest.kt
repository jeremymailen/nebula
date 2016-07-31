package org.jmailen.nebula.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.hamcrest.CoreMatchers.equalTo
import org.jmailen.nebula.Service
import org.jmailen.nebula.infrastructure.messaging.MessagingPubSub
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
fun TestRestTemplate.getHealth() = get(HEALTH_API_PATH)

data class TestMessage(var id: Int, var text: String)

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Service::class), webEnvironment = RANDOM_PORT)
class ServiceIntegrationTest() {
    val json = jacksonObjectMapper()

    @Autowired lateinit var rest: TestRestTemplate;

    @Test fun healthApi() {
        val result = rest.getHealth()

        assertThat(result.statusCode, equalTo(OK))
        assertEquals("Nebula", result.body["name"])
        assertEquals("unknown", result.body["version"])
    }

    @Autowired lateinit var pubsub: MessagingPubSub

    @Test(timeout = 1000L)
    fun mqttMessaging() {
        val message = TestMessage(1, "Hello")
        var receivedMessage: TestMessage? = null

        pubsub.subscribe("test", fun(message: ByteArray) {
            receivedMessage = json.readValue(message)
        })
        pubsub.publish("test", json.writeValueAsBytes(message))

        while (receivedMessage == null) {}
        assertThat(receivedMessage, equalTo(message))
    }
}
