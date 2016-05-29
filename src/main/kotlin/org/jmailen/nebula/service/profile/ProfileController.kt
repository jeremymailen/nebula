package org.jmailen.nebula.service.profile

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

const val PROFILE_API_PATH = "/api/profiles"

@RestController
@RequestMapping(PROFILE_API_PATH)
class ProfileController(val store: ProfileStore) {

    @GetMapping
    fun list() = store.list()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int) = store.get(id)

    @DeleteMapping
    fun deleteAll(res: HttpServletResponse) {
        store.clear()
        res.status = HttpStatus.NO_CONTENT.value()
    }
}
