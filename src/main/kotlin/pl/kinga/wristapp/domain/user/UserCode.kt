package pl.kinga.wristapp.domain.user

import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("user_codes")
@TypeAlias("UserCode")
class UserCode(
    @Indexed(unique = true)
    val code: String,
    val createdAt: Instant = Instant.now(),
    var id: String? = null,
    val description: String?,
    val creator: String
)
