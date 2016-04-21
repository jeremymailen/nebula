package org.jmailen.nebula.service.profiles

import org.springframework.stereotype.Component
import java.util.HashSet

data class Profile(var name: String, var phone: String)

@Component
class ProfileStore {
    val profileSet = HashSet<Profile>()

    init {
        profileSet.add(Profile(name = "Jeremy Malken", phone = "214123456"))
        profileSet.add(Profile(name = "Ray Norquist", phone = "415123456"))
    }

    fun list(): Iterable<Profile> {
        return profileSet.asIterable()
    }
}
