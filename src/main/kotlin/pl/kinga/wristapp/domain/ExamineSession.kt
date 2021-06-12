package pl.kinga.wristapp.domain

import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("examine_sessions")
@TypeAlias("ExamineSession")
class ExamineSession(
    val code: String,
    val sessionTimestamp: Instant,
    val examines: MutableList<Examine> = mutableListOf(),
    val deviceHeight: Int,
    val deviceWidth: Int,
    var id: String? = null
)
