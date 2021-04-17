package dev.tieris.springsecurity.domain.dto

import dev.tieris.springsecurity.domain.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(user: UserEntity): UserDetails {

    private val id: String = user.id
    private val username: String = user.name
    private val password: String = user.password
    private val active: Boolean = user.active
    private val authorities: MutableList<GrantedAuthority> = user.roles.split(",").map{ SimpleGrantedAuthority(it) }.toMutableList()

    fun getId() = this.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.authorities
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return active
    }
}
