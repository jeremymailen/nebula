package org.jmailen.nebula.service.health

import org.jmailen.nebula.ServiceMetadata
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController {

    @RequestMapping("")
    fun health() = ServiceMetadata
}
