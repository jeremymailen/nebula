package org.jmailen.nebula.service.health

import org.jmailen.nebula.ServiceMetadata
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val HEALTH_API_PATH = "/health"

@RestController
@RequestMapping(HEALTH_API_PATH)
class HealthController {

    @RequestMapping("")
    fun health() = ServiceMetadata
}
