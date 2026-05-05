package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.WeeklyStatsRequestDto

class StatsRemoteDataSource(
    private val api: HabitHeroApi
) {

    suspend fun getWeeklyStats(
        habitId: Int,
        start: String,
        end: String
    ) = api.getWeeklyStats(
        WeeklyStatsRequestDto(
            habitId = habitId,
            start = start,
            end = end
        )
    )
}