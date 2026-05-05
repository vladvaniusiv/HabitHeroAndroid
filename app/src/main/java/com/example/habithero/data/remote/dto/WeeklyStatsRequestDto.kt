package com.example.habithero.data.remote.dto

data class WeeklyStatsRequestDto(
    val habitId: Int,
    val start: String,
    val end: String
)
