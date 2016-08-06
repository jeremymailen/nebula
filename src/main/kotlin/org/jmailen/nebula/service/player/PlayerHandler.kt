package org.jmailen.nebula.service.player

import org.jmailen.nebula.infrastructure.messaging.MessagingPubSub
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Service

const val PLAYER_JOIN_TOPIC = "client/player/join"
const val PLAYER_ADD_TOPIC = "server/player/add"

@Service
class PlayerHandler(val pubsub: MessagingPubSub) {
    val logger = getLogger(javaClass)

    init {
        pubsub.subscribe(PLAYER_JOIN_TOPIC, Player::class.java) { handlePlayerJoin(it) }
    }

    fun handlePlayerJoin(player: Player) {
        logger.info("Player joined: {}", player)
        publishAddPlayer(player)
    }

    fun publishAddPlayer(player: Player) {
        pubsub.publish(PLAYER_ADD_TOPIC, player)
    }
}
