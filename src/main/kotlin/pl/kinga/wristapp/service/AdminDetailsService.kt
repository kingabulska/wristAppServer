package pl.kinga.wristapp.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import pl.kinga.wristapp.domain.admin.AdminUser
import pl.kinga.wristapp.repository.AdminRepository

@Service
class AdminDetailsService(
    private val adminRepo: AdminRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return adminRepo.findByUsername(username)
            ?.let { AdminPrincipal(it) }
            ?: throw UsernameNotFoundException(username)
    }

}

class AdminPrincipal(
    private val admin: AdminUser
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = admin.authorities.map { SimpleGrantedAuthority(it.name) }.toMutableList()

    override fun getPassword(): String = admin.password

    override fun getUsername(): String = admin.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
