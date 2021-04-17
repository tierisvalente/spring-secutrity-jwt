package dev.tieris.springsecurity.repository

import dev.tieris.springsecurity.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserEntity, String> {

    fun findByName(name: String): Optional<UserEntity>

    fun existsByName(name: String): Boolean
}