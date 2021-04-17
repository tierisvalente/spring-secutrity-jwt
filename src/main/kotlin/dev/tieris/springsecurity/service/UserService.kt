package dev.tieris.springsecurity.service

import dev.tieris.springsecurity.domain.dto.UserDetail
import dev.tieris.springsecurity.domain.entity.UserEntity
import dev.tieris.springsecurity.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(name: String): UserDetails {
        val user: Optional<UserEntity> = userRepository.findByName(name)
        user.orElseThrow { UsernameNotFoundException("Not found: $name") }

        return user.map { UserDetail(it) }.get()
    }

    fun existsByName(name: String) = userRepository.existsByName(name)

    fun save(user: UserEntity) {
        userRepository.save(user)
    }
}