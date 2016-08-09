package org.jmailen.nebula.service.player

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.Spek
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.`is` as eqTo

class PlayerControllerTest : Spek({
    describe("PlayerControllerTest") {
        val store: PlayerStore = mock()
        val subject = PlayerController(store)

        on("get") {
            val testPlayer = Player(id = "1", name = "Roscoe")

            it("returns profile for id") {
                whenever(store.get("1")).thenReturn(testPlayer)
                assertThat(subject.get("1"), eqTo(testPlayer))
                verify(store, times(1)).get("1")
            }
        }
    }
})