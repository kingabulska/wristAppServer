package pl.kinga.wristapp.controller

import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.stream
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.kinga.wristapp.domain.ExamineSession
import pl.kinga.wristapp.repository.ExamineSessionRepository

@RestController
@RequestMapping("/recalc")
class CalculationController(
    private val mongoTemplate: MongoTemplate
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun recalcCosineArray(): String {
        mongoTemplate.stream(Query(), ExamineSession::class.java)
            .forEach {
                mongoTemplate.save(it.apply { it.examines.forEach { it.recalc() } })
                log.debug("Handled ${it.id}")
            }

        return "przeliczono"
    }

    @GetMapping("/numbers")
    fun setNumbers(): String {
        mongoTemplate.stream(Query(), ExamineSession::class.java)
            .forEach {
                mongoTemplate.save(it.apply { it.examines.forEachIndexed { idx, ex ->
                    ex.number = (idx / 2) + 1
                    ex.renders?.forEachIndexed { idx, it ->
                        it.hand = ex.hand
                        it.examineNumber = ex.number
                        it.ellipseNumber = idx + 1
                    }
                } })
                log.debug("Handled ${it.id}")
            }

        return "przeliczono"
    }
}
