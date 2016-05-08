package org.jmailen.nebula.service.profile

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ProfileStoreTest() {

    /**
     * lateinit satisfies non-nullable. Other way would be to delegate the property:
     * ```
     * var subject: ProfileStore by Delegates.notNull()
     * ```
     */
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

    @Test fun updateWithNew() {
        val george = Profile(name = "George Georgio", phone = "+19161234567")
        val result = subject.update(george)
        assertThat(result, equalTo(george to ProfileStatus.CREATED))
    }

    @Test fun updateExisting() {
        val modJeremy = jeremy.copy()
        modJeremy.phone = "+14153217654"
        val result = subject.update(modJeremy)
        assertThat(result, equalTo(jeremy to ProfileStatus.REPLACED))
    }

    @Test fun clear() {
        subject.clear()
        assertThat(subject.profileMap.size, equalTo(0))
    }
}