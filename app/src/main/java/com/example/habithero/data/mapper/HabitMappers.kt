package com.example.habithero.data.mapper

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.data.local.entity.HabitEntity
import com.example.habithero.data.remote.dto.HabitDto

// DTO → Entity
fun HabitDto.toEntity() = HabitEntity(
    id = id ?: 0,
    userId = userId,
    title = title,
    description = description,
    active = active
)

// Entity → Domain
fun HabitEntity.toDomain() = Habit(
    id = id,
    userId = userId,
    title = title,
    description = description,
    active = active
)

// Domain → Entity
fun Habit.toEntity() = HabitEntity(
    id = id ?: 0,
    userId = userId,
    title = title,
    description = description,
    active = active
)

// Domain → DTO
fun Habit.toDto() = HabitDto(
    id = id,
    userId = userId,
    title = title,
    description = description,
    active = active
)

// DTO → Domain
fun HabitDto.toDomain() = Habit(
    id = id,
    userId = userId,
    title = title,
    description = description,
    active = active
)