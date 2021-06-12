package pl.kinga.wristapp.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.kinga.wristapp.domain.ExamineSession
import java.time.Instant

interface ExamineSessionRepository : MongoRepository<ExamineSession, String> {

    fun findBySessionTimestampAndCode(sessionTimestamp: Instant, code: String): ExamineSession?

    fun findAllByCode(code: String): List<ExamineSession>

    fun findAllByCodeAndIdIn(code: String, sessions: List<String>): List<ExamineSession>
}
