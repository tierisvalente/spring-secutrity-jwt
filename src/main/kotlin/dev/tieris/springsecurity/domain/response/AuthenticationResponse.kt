package dev.tieris.springsecurity.domain.response

data class AuthenticationResponse(
    val id: String,
    val userName: String,
    val jwt: String,
    val roles: List<String>
)
