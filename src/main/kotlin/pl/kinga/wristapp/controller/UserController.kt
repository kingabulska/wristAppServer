package pl.kinga.wristapp.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import pl.kinga.wristapp.domain.*
import pl.kinga.wristapp.domain.admin.AdminRole
import pl.kinga.wristapp.domain.user.UserCode
import pl.kinga.wristapp.dto.export
import pl.kinga.wristapp.dto.toDto
import pl.kinga.wristapp.repository.ExamineSessionRepository
import pl.kinga.wristapp.repository.UserCodeRepository
import pl.kinga.wristapp.util.RandomString
import java.time.Instant

@Controller
@RequestMapping("/users")
class UserController(
    private val userCodeRepo: UserCodeRepository,
    private val examineRepo: ExamineSessionRepository,
    private val mapper: ObjectMapper
) {

    @GetMapping
    fun users(model: Model): String {
        val princip =
            SecurityContextHolder.getContext().authentication.principal as UserDetails

        val users = userCodeRepo.findAllByCreator(princip.username)

        model["users"] = users
        model["isSuperAdmin"] = princip.authorities.any { it.authority == AdminRole.Super.name }

        return "users"
    }

    @GetMapping("/create")
    fun getCreateUserForm(): String = "user_create"

    @PostMapping("/create")
    fun createUser(@ModelAttribute("userDto") userDto: UserDto): String {
        val princip = SecurityContextHolder.getContext().authentication.principal as UserDetails

        val code = RandomString.upper().nextString()
        val user = UserCode(code, description = userDto.description, creator = princip.username)
        userCodeRepo.save(user)
        return "redirect:/users"
    }

    @GetMapping("/{id}/download")
    fun downloadUserData(@PathVariable id: String, @RequestParam sessions: List<String>): ResponseEntity<Resource>? {
        val princip = SecurityContextHolder.getContext().authentication.principal as UserDetails

        val user = userCodeRepo.findByIdAndCreator(id, princip.username)

        if (user != null) {
            val dtos = examineRepo.findAllByCodeAndIdIn(user.code, sessions)
                .export()

            val mappedToByteArray = mapper.writeValueAsBytes(dtos)

            val res = ByteArrayResource(mappedToByteArray)
            val type = MediaType.APPLICATION_OCTET_STREAM
            val headers = HttpHeaders()
                .apply {
                    contentType = type
                    contentDisposition = ContentDisposition.inline().filename("data.json").build()
                }

            return ResponseEntity(res, headers, HttpStatus.OK)
        }

        return null
    }

    @GetMapping("/{id}/sessions")
    fun getUserData(@PathVariable id: String, model: Model): String {
        val princip = SecurityContextHolder.getContext().authentication.principal as UserDetails

        val user = userCodeRepo.findByIdAndCreator(id, princip.username)

        if (user != null) {
            val sessions = examineRepo.findAllByCode(user.code)
            model["sessions"] = sessions
            model["user"] = user
        }

        return "user_sessions"
    }

    @GetMapping("/{id}/sessions/{sessionId}")
    fun getSessionData(@PathVariable id: String, @PathVariable sessionId: String, model: Model): String {
        val princip = SecurityContextHolder.getContext().authentication.principal as UserDetails

        val user = userCodeRepo.findByIdAndCreator(id, princip.username)

        if (user != null) {
            val session = examineRepo.findByIdOrNull(sessionId)
            if (session != null) {
                model["session"] = session
                model["user"] = user

                val left = session.examines.filter { it.hand == Hand.Left }
                val leftExamine1 = left.firstOrNull()?.toView()
                val leftExamine1Ellipse1 = leftExamine1?.recordings?.firstOrNull { it.ellipse.id == 1 }
                val leftExamine1Ellipse2 = leftExamine1?.recordings?.firstOrNull { it.ellipse.id == 2 }
                val leftExamine1Ellipse3 = leftExamine1?.recordings?.firstOrNull { it.ellipse.id == 3 }

                val leftExamine2 = left.getOrNull(1)?.toView()
                val leftExamine2Ellipse1 = leftExamine2?.recordings?.firstOrNull { it.ellipse.id == 1 }
                val leftExamine2Ellipse2 = leftExamine2?.recordings?.firstOrNull { it.ellipse.id == 2 }
                val leftExamine2Ellipse3 = leftExamine2?.recordings?.firstOrNull { it.ellipse.id == 3 }

                val right = session.examines.filter { it.hand == Hand.Right }
                val rightExamine1 = right.firstOrNull()?.toView()
                val rightExamine1Ellipse1 = rightExamine1?.recordings?.firstOrNull { it.ellipse.id == 1 }
                val rightExamine1Ellipse2 = rightExamine1?.recordings?.firstOrNull { it.ellipse.id == 2 }
                val rightExamine1Ellipse3 = rightExamine1?.recordings?.firstOrNull { it.ellipse.id == 3 }

                val rightExamine2 = right.getOrNull(1)?.toView()
                val rightExamine2Ellipse1 = rightExamine2?.recordings?.firstOrNull { it.ellipse.id == 1 }
                val rightExamine2Ellipse2 = rightExamine2?.recordings?.firstOrNull { it.ellipse.id == 2 }
                val rightExamine2Ellipse3 = rightExamine2?.recordings?.firstOrNull { it.ellipse.id == 3 }

                val renders = listOfNotNull(
                    left, right
                )
                    .flatten()
                    .sortedBy { it.start }
                    .mapNotNull { it.renders }
                    .flatten()
                    .map { it.scaled(0.30) }

                val attrs = mutableMapOf(
                    "leftExamine1" to leftExamine1,
                    "leftExamine1Ellipse1" to leftExamine1Ellipse1,
                    "leftExamine1Ellipse2" to leftExamine1Ellipse2,
                    "leftExamine1Ellipse3" to leftExamine1Ellipse3,

                    "leftExamine2" to leftExamine2,
                    "leftExamine2Ellipse1" to leftExamine2Ellipse1,
                    "leftExamine2Ellipse2" to leftExamine2Ellipse2,
                    "leftExamine2Ellipse3" to leftExamine2Ellipse3,

                    "rightExamine1" to rightExamine1,
                    "rightExamine1Ellipse1" to rightExamine1Ellipse1,
                    "rightExamine1Ellipse2" to rightExamine1Ellipse2,
                    "rightExamine1Ellipse3" to rightExamine1Ellipse3,

                    "rightExamine2" to rightExamine2,
                    "rightExamine2Ellipse1" to rightExamine2Ellipse1,
                    "rightExamine2Ellipse2" to rightExamine2Ellipse2,
                    "rightExamine2Ellipse3" to rightExamine2Ellipse3,

                    "renders" to renders
                )

                model.addAllAttributes(attrs)
            }
        }

        return "session"
    }
}

data class UserDto(
    val description: String? = null
)

data class ExamineView(
    val deviceHeight: Int,
    val deviceWidth: Int,
    val hand: Hand,
    val start: Instant,
    val end: Instant,
    val recordings: List<RecordingView>,
    val sessionTimestamp: Instant,
    val renders: List<EllipseRender>?
)

data class RecordingView(
    val ellipse: Ellipse,
    val drawing: List<TimePositionView>,
    val start: Instant?,
    val end: Instant?,
    val cosArrayMean: Double?,
)

data class TimePositionView(
    val x: Float,
    val y: Float,
    val cos: Double?,
    val timestamp: Long
)

fun Examine.toView() = ExamineView(
    deviceHeight = deviceHeight,
    deviceWidth = deviceWidth,
    hand = hand,
    start = start,
    end = end,
    recordings = recordings.mapIndexed { idx, it -> it.toView(renders?.getOrNull(idx)) },
    sessionTimestamp = sessionTimestamp,
    renders = renders,
)

fun Recording.toView(render: EllipseRender?): RecordingView {

    val firstTimestamp = drawing.firstOrNull()

    val drawingView = firstTimestamp?.timestamp?.let { tm ->
        drawing.mapIndexed { idx, it ->
            TimePositionView(
                x = it.x,
                y = it.y,
                cos = render?.cosineArray?.getOrNull(idx),
                timestamp = it.timestamp.toEpochMilli() - tm.toEpochMilli()
            )
        }
    }
        ?: listOf()

    return RecordingView(
        ellipse = ellipse,
        drawing = drawingView,
        start = start,
        end = end,
        cosArrayMean = render?.cosArrayMean
    )
}
