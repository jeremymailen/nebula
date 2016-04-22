package org.jmailen.nebula.service.profile

import org.springframework.stereotype.Component
import java.util.*

data class Profile(var name: String, var phone: String)

@Component
class ProfileStore(val profileSet: MutableSet<Profile> = HashSet<Profile>()) {

    init {
        profileSet.add(Profile(name = "Jeremy Malken", phone = "214123456"))
        profileSet.add(Profile(name = "Ray Norquist", phone = "415123456"))
    }

    fun list() = profileSet.asIterable()

    fun clear() {
        profileSet.clear()
    }
}
