package pl.kinga.wristapp.domain.admin

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("admins")
class AdminUser(
    @Id
    val username: String,
    var password: String,
    val authorities: Set<AdminRole>,
    val name: String?,
    val surname: String?,
)

enum class AdminRole {
    Regular, Super
}
