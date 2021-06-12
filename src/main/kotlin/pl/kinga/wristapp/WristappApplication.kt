package pl.kinga.wristapp

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import pl.kinga.wristapp.domain.admin.AdminRole
import pl.kinga.wristapp.domain.admin.AdminUser
import pl.kinga.wristapp.repository.AdminRepository

@SpringBootApplication
class WristappApplication

fun main(args: Array<String>) {
    runApplication<WristappApplication>(*args)
}

@Component
class InitAdmin(
    private val adminRepository: AdminRepository,
    private val encoder: PasswordEncoder
) : InitializingBean {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun afterPropertiesSet() {
        log.info("Setting up admin account")

        val admE = adminRepository.findByUsername("admin")
        if (admE != null) {
            log.info("Admin already exist. Skipping.")
            return
        }

        val encodedPwd = encoder.encode("pass")
        val adm = AdminUser(
            "admin", encodedPwd, setOf(AdminRole.Super), "Adam", "Mickiewicz",
        )

        adminRepository.save(adm)

        log.info("Initialized admin account")
    }


}
