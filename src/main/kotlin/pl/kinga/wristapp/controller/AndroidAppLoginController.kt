package pl.kinga.wristapp.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.kinga.wristapp.repository.UserCodeRepository

@RestController
@RequestMapping("/api/login")
class AndroidAppLoginController(
    private val userCodeRepository: UserCodeRepository
) {

    @PostMapping
    fun getLogin(@RequestBody login: LoginRequest): ResponseEntity<Any> {
       val user= userCodeRepository.findByCode(login.code)

        return if(user != null) ResponseEntity(HttpStatus.OK)
        else ResponseEntity(HttpStatus.UNAUTHORIZED)
    }
}

data class LoginRequest(
    val code: String
)
