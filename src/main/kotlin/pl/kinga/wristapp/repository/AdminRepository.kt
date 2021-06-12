package pl.kinga.wristapp.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.kinga.wristapp.domain.admin.AdminUser

interface AdminRepository : MongoRepository<AdminUser, String> {
    fun findByUsername(username: String): AdminUser?
}
