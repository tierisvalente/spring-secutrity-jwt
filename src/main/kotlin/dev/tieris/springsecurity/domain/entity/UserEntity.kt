package dev.tieris.springsecurity.domain.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "USER_DETAIL")
data class UserEntity(
    @Id
    @Column(name = "ID")
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "NAME")
    val name: String,

    @Column(name = "PASSWORD")
    val password: String,

    @Column(name = "ACTIVE")
    val active: Boolean,

    @Column(name = "ROLE")
    val roles: String
)
