package pl.kinga.wristapp.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kinga.wristapp.domain.Examine
import pl.kinga.wristapp.domain.ExamineSession
import pl.kinga.wristapp.repository.ExamineSessionRepository
import pl.kinga.wristapp.repository.UserCodeRepository

@RestController
@RequestMapping("/api/examines")
class DataInputController(
    private val userCodeRepo: UserCodeRepository,
    private val sessionRepository: ExamineSessionRepository
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun receiveData(
        @RequestBody examine: Examine,
        @RequestHeader("X-code") code: String
    ): ResponseEntity<Any> {
        userCodeRepo.findByCode(code)
            ?: return ResponseEntity(HttpStatus.FORBIDDEN)

        val session = sessionRepository.findBySessionTimestampAndCode(examine.sessionTimestamp, code)
            ?: ExamineSession(
                code = code,
                sessionTimestamp = examine.sessionTimestamp,
                deviceHeight = examine.deviceHeight,
                deviceWidth = examine.deviceWidth
            )

        session.examines.add(examine)
        session.examines.forEachIndexed { index, ex -> ex.number = (index / 2)  + 1 }

        sessionRepository.save(session)

        log.info(examine.toString())

        return ResponseEntity.ok().build()
    }
}
