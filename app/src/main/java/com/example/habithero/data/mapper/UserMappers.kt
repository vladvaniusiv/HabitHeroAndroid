package com.example.habithero.data.mapper

import com.example.habithero.core.domain.model.User
import com.example.habithero.data.local.entity.UserEntity
import com.example.habithero.data.remote.dto.LoginResponseDto
import com.example.habithero.data.remote.dto.UserDto

// DTO → Domain
fun UserDto.toDomain() = User(
    id = id,
    name = name,
    username = username,
    email = email,
    password = ""
)

fun UserDto.toEntity() = UserEntity(
    id = id ?: 0,
    name = name,
    username = username,
    email = email
)

// Domain → DTO
fun User.toDto() = UserDto(
    id = id,
    name = name,
    email = email,
    username = username,
    password = ""
)

// Entity → Domain
fun UserEntity.toDomain() = User(
    id = id,
    name = name,
    username = this.username ?: this.email.substringBefore("@"),
    email = email,
    password = ""
)

// Domain → Entity
fun User.toEntity() = UserEntity(
    id = id ?: 0,
    name = name,
    username = username,
    email = email
)

fun LoginResponseDto.toDomainUser(): User {
    return User(
        id = this.userId,
        name = this.name,
        username = this.username ?: this.email.substringBefore("@"),
        email = this.email,
        password = ""
    )
}
