package org.jmailen.nebula.service.health

import org.jmailen.nebula.serviceMetadata
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @RequestMapping("/health")
    fun health(): serviceMetadata {
        return serviceMetadata
    }
}
