package com.example.habithero.core.domain.model

data class HabitProgress(
    val id: Int? = null,
    val habitId: Int,
    val date: String,
    val completed: Boolean
)
