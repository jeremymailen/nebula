package org.jmailen.nebula.service.profile

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ProfileStoreTest() {
   lateinit var subject: ProfileStore

    @Before fun setup() {
        subject = ProfileStore()
    }

    @Test fun list() {
        val results = subject.list()
        assertEquals(2, results.size)
        assertThat(results, hasItems(jeremy, ray))
    }

    @Test fun get() {
        assertEquals(ray, subject.get(2))
        assertNull(subject.get(999))
    }

    @Test fun clear() {
        subject.clear()
        assertThat(subject.profileMap.size, equalTo(0))
    }
}

@RunWith(Parameterized::class)
class ProfileStoreUpdateTest(val input: Profile, val expected: Pair<Profile, ProfileStatus>) {

    companion object {
        val newJeremy = Profile(1, "Jeremy Malken", "+14153217654")
        val george = Profile(3, "George Georgio", "+19161234567")

        @JvmStatic @Parameters(name = "{0} => {1}")
        fun data() = listOf(
                arrayOf(george, george to ProfileStatus.CREATED),
                arrayOf(ray, ray to ProfileStatus.REPLACED),
                arrayOf(newJeremy, jeremy to ProfileStatus.REPLACED)
        )
    }

    lateinit var subject: ProfileStore

    @Before fun setup() {
        subject = ProfileStore()
    }

    @Test fun update() {
        assertThat(subject.update(input), equalTo(expected))
    }
}
