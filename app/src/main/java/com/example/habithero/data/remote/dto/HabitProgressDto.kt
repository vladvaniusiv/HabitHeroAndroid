package com.example.habithero.data.remote.dto

data class HabitProgressDto(
    val id: Int?,
    val habitId: Int,
    val date: String,
    val completed: Boolean
)