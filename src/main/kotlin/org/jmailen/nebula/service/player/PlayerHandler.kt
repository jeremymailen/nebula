package org.jmailen.nebula.service.player

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jmailen.nebula.infrastructure.messaging.MessagingPubSub
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Service

const val PLAYER_JOIN_TOPIC = "client/player/join"
const val PLAYER_ADD_TOPIC = "server/player/add"

@Service
class PlayerHandler(val pubsub: MessagingPubSub) {
    val logger = getLogger(this.javaClass)
    val json = jacksonObjectMapper()

    init {
        pubsub.subscribe(PLAYER_JOIN_TOPIC, fun(payload: ByteArray) {
            handlePlayerJoin(json.readValue(payload))
        })
    }

    fun handlePlayerJoin(player: Player) {
        logger.info("Player joined: {}", player)
        publishAddPlayer(player)
    }

    fun publishAddPlayer(player: Player) {
        pubsub.publish(PLAYER_ADD_TOPIC, json.writeValueAsBytes(player))
    }
}
