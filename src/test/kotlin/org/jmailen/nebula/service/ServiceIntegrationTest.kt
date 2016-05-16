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
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Service::class), webEnvironment = RANDOM_PORT)
class ServiceIntegrationTest() {

    @Autowired lateinit var rest: TestRestTemplate;

    @Test fun healthApi() {
        val result = rest.getForObject(HEALTH_API_PATH, String::class.java)
        assertThat(result, equalTo("""{"name":"Nebula","version":"unknown"}"""))
    }
}
