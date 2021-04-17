package dev.tieris.springsecurity.domain

import java.time.LocalDateTime

data class MessageResponse(
    val message: String,
    val date: LocalDateTime = LocalDateTime.now()
)
