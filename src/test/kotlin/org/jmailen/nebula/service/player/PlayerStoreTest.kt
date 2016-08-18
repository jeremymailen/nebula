package org.jmailen.nebula.service.player

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class PlayerStoreTest {
    lateinit var subject: PlayerStore
    val player = Player("1", "Picard")

    @Before fun setup() {
        subject = PlayerStore()
        subject.handler = mock()
    }

    @Test fun add() {
        subject.add(player)

        assertThat(subject.get("1"), equalTo(player))
        verify(subject.handler).publishAddPlayer(player)
    }

    @Test fun update() {
        subject.add(player)
        subject.update(Player("1", "Locutus of Borg"))

        assertThat(subject.get("1")?.name, equalTo("Locutus of Borg"))
    }

    @Test(expected = EntityNotFoundException::class)
    fun updateNonExistent() {
        subject.update(player)
    }
}
