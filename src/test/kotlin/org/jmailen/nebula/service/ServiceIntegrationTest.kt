package org.jmailen.nebula.service

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
}
