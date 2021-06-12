package pl.kinga.wristapp.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.kinga.wristapp.domain.user.UserCode

interface UserCodeRepository : MongoRepository<UserCode, String> {

    fun findByCode(code: String): UserCode?

    fun findAllByCreator(creator: String): List<UserCode>

    fun findByIdAndCreator(id: String, creator: String): UserCode?
}
