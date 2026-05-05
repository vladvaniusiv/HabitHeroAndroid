package com.example.habithero.data.mapper

import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.data.local.entity.HabitProgressEntity
import com.example.habithero.data.remote.dto.HabitProgressDto

// DTO → Entity
fun HabitProgressDto.toEntity() = HabitProgressEntity(
    id = id ?: 0,
    habitId = habitId,
    date = date,
    completed = completed
)

// Entity → Domain
fun HabitProgressEntity.toDomain() = HabitProgress(
    id = id,
    habitId = habitId,
    date = date,
    completed = completed
)

// Domain → Entity
fun HabitProgress.toEntity() = HabitProgressEntity(
    id = id ?: 0,
    habitId = habitId,
    date = date,
    completed = completed
)

// Domain → DTO
fun HabitProgress.toDto() = HabitProgressDto(
    id = id,
    habitId = habitId,
    date = date,
    completed = completed
)

// DTO → Domain
fun HabitProgressDto.toDomain() = HabitProgress(
    id = id,
    habitId = habitId,
    date = date,
    completed = completed
)