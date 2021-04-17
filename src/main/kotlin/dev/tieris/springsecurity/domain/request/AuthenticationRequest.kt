package dev.tieris.springsecurity.domain.request

data class AuthenticationRequest(
    val userName: String,
    val password: String
)
