package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.WeeklyStatsRequestDto
import com.example.habithero.data.remote.dto.HabitProgressDto

class StatsRemoteDataSource(
    private val api: HabitHeroApi
) {

    suspend fun getWeeklyStats(
        token: String,
        habitId: Int,
        start: String,
        end: String
    ): List<HabitProgressDto> = api.getWeeklyStats(
        token = "Bearer $token",
        body = WeeklyStatsRequestDto(
            habitId = habitId,
            start = start,
            end = end
        )
    )
}