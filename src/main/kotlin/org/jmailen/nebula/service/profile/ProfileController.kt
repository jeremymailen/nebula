package org.jmailen.nebula.service.profile

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

const val PROFILE_API_PATH = "/profiles"

@RestController
@RequestMapping(PROFILE_API_PATH)
class ProfileController @Autowired constructor(val store: ProfileStore) {

    @RequestMapping("")
    fun list() = store.list()

    @RequestMapping("/{id}")
    fun get(@PathVariable id: Int) = store.get(id)

    @RequestMapping(path = arrayOf(""), method = arrayOf(RequestMethod.DELETE))
    fun deleteAll(res: HttpServletResponse) {
        store.clear()
        res.status = HttpStatus.NO_CONTENT.value()
    }
}
