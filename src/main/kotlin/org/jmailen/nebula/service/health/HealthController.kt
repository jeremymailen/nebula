package org.jmailen.nebula.service.health

import org.jmailen.nebula.serviceMetadata
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController {

    @RequestMapping("")
    fun health() = serviceMetadata
}
