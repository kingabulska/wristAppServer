package pl.kinga.wristapp.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import pl.kinga.wristapp.domain.admin.AdminRole
import pl.kinga.wristapp.domain.admin.AdminUser
import pl.kinga.wristapp.repository.AdminRepository

@Controller
@RequestMapping("/admins")
class AdminUserController(
    private val encoder: PasswordEncoder,
    private val adminRepo: AdminRepository
) {

    private val createAdminErrors = mapOf(
        "1" to "Taki login juz istnieje",
        "2" to "Haslo i login nie moga byc puste",
        "3" to "Nie masz odpowiednich uprawnien"
    )

    @GetMapping
    fun getCreateForm(model: Model, @RequestParam error: String?): String {
        val admins = adminRepo.findAll()
        model["admins"] = admins
        model.addAttribute("errorMessage", createAdminErrors[error])
        return "admins"
    }

    @PostMapping
    fun createAdmin(@ModelAttribute("adminDto") adminDto: AdminDto): String {
        if (adminRepo.findByUsername(adminDto.username) != null) {
            return "redirect:/admins?error=1"
        }

        if(adminDto.password.isBlank() || adminDto.username.isBlank()) {
            return "redirect:/admins?error=2"
        }

        val princip =
            SecurityContextHolder.getContext().authentication.principal as UserDetails

        if (princip.authorities.any { it.authority != AdminRole.Super.name }) {
            return "redirect:/admins?error=3"
        }

        val admn = AdminUser(
            username = adminDto.username,
            password = encoder.encode(adminDto.password),
            authorities = setOf(AdminRole.Regular),
            name = adminDto.name,
            surname = adminDto.surname,
        )

        adminRepo.save(admn)

        return "redirect:/admins"
    }

    @GetMapping("/password")
    fun getPasswordChangeForm(@RequestParam errorMessage: String?, @RequestParam success: String?, model: Model): String {
        model.addAttribute("errorMessage", errorMessage)
        model.addAttribute("success", success)
        return "admin_passwords"
    }

    @PostMapping("/password")
    fun change(changePassword: PasswordChange): String {
        val princip = SecurityContextHolder.getContext().authentication.principal as UserDetails

        val admin = adminRepo.findByUsername(princip.username)
            ?: return "redirect:/admins/password?errorMessage=uzytkownik nie istnieje"

        if (!encoder.matches(changePassword.previousPassword, admin.password)) {
            return "redirect:/admins/password?errorMessage=bledne haslo"
        }

        if(changePassword.newPassword1.isBlank()){
            "redirect:/admins/password?errorMessage=haslo nie moze byc puste"
        }

        if(changePassword.newPassword1 != changePassword.newPassword2) {
            return "redirect:/admins/password?errorMessage=hasla nie sa identyczne"
        }

        admin.password = encoder.encode(changePassword.newPassword1)

        adminRepo.save(admin)

        return "redirect:/admins/password?success=true"
    }
}

class AdminDto(
    val name: String,
    val surname: String,
    val password: String,
    val username: String
)

class PasswordChange(
    val previousPassword: String,
    val newPassword1: String,
    val newPassword2: String
)
