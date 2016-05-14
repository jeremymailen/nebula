package org.jmailen.nebula.service.profile

import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger


val ProfileIds = AtomicInteger(1)
data class Profile(var id: Int = ProfileIds.andIncrement, var name: String, var phone: String)

enum class ProfileStatus {
    CREATED, REPLACED, DELETED
}

/**
 * Example of a CRUD store in memory with data class contents and idiomatic collection operations.
 *
 * @param profileMap initial content of store, defaults to test values.
 */
@Component
open class ProfileStore(val profileMap: MutableMap<Int, Profile> = testProfiles()) {

    fun list() = profileMap.values

    open fun get(id: Int) = profileMap[id]

    fun update(profile: Profile): Pair<Profile, ProfileStatus> {
        val original = profileMap[profile.id]
        profileMap += profile.id to profile

        return when (original != null) {
            true  -> original!! to ProfileStatus.REPLACED
            false -> profile to ProfileStatus.CREATED
        }
    }

    fun clear() {
        profileMap.clear()
    }
}

val jeremy = Profile(name = "Jeremy Malken", phone = "+12141234567")
val ray = Profile(name = "Ray Norquist", phone = "+14151234567")
fun testProfiles() = hashMapOf(jeremy.id to jeremy, ray.id to ray)
