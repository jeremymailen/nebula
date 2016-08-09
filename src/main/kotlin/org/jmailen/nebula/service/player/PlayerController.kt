package org.jmailen.nebula.service.player

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

const val PLAYER_API_PATH = "/api/players"

@RestController
@RequestMapping(PLAYER_API_PATH)
class PlayerController(val store: PlayerStore) {

    @GetMapping
    fun list() = store.list()

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) = store.get(id)

    @DeleteMapping
    fun deleteAll(res: HttpServletResponse) {
        store.clear()
        res.status = HttpStatus.NO_CONTENT.value()
    }
}
