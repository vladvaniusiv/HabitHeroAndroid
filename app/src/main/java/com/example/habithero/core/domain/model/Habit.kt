package com.example.habithero.core.domain.model

data class Habit(
    val id: Int? = null,
    val userId: Int,
    val title: String,
    val description: String,
    val active: Boolean = true
)
