package org.jmailen.nebula.service.profile

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.Spek
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.`is` as eqTo

class ProfileControllerTest : Spek({
    describe("ProfileController") {
        var store: ProfileStore = mock()
        var subject = ProfileController(store)

        on("get") {
            val testProfile = Profile(name = "Bob", phone = "999")

            it("returns profile for id") {
                whenever(store.get(1)).thenReturn(testProfile)
                assertThat(subject.get(1), eqTo(testProfile))
                verify(store, times(1)).get(1)
            }
        }
    }
})
