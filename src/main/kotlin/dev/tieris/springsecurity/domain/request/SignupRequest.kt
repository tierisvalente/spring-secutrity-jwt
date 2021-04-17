package dev.tieris.springsecurity.domain.request

import dev.tieris.springsecurity.domain.enums.UserRole

data class SignupRequest(
    val name: String,
    val password: String,
    val role: String = UserRole.ROLE_USER.role
)
