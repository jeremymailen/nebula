package org.jmailen.nebula.service.profile

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

@RestController
@RequestMapping("/profile")
class ProfileController @Autowired constructor(val store: ProfileStore) {

    @RequestMapping("")
    fun list(): Iterable<Profile> {
        return store.list()
    }
}
