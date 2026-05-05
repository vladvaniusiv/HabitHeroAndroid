package com.example.habithero.data.mapper

import com.example.habithero.core.domain.model.User
import com.example.habithero.data.local.entity.UserEntity
import com.example.habithero.data.remote.dto.UserDto

// DTO → Domain
fun UserDto.toDomain() = User(
    id = id,
    name = name,
    username = name.lowercase(),
    email = email,
    password = password
)

// Domain → DTO
fun User.toDto() = UserDto(
    id = id,
    name = name,
    email = email,
    password = password
)

// Entity → Domain
fun UserEntity.toDomain() = User(
    id = id,
    name = name,
    username = name.lowercase(),
    email = email,
    password = ""
)

// Domain → Entity
fun User.toEntity() = UserEntity(
    id = id ?: 0,
    name = name,
    email = email
)
