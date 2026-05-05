package com.example.habithero.data.remote.dto

data class HabitDto(
    val id: Int?,
    val userId: Int,
    val title: String,
    val description: String,
    val active: Boolean
)
