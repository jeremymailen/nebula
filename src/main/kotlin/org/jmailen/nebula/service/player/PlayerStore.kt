package org.jmailen.nebula.service.player

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
open class PlayerStore() {
    lateinit var handler: PlayerHandler
    val players = ConcurrentHashMap<String, Player>()

    fun list() = players.values

    open fun get(id: String) = players[id]

    fun add(player: Player) {
        players[player.id] = player
        handler.publishAddPlayer(players[player.id]!!)
    }

    fun update(player: Player) {
        if (players[player.id] == null) {
            throw EntityNotFoundException("player ${player.id} not found")
        }
        players[player.id] = player
    }

    fun delete(id: String) {
        players.remove(id)
    }

    fun clear() = players.clear()
}

class EntityNotFoundException(override val message: String) : RuntimeException(message)
