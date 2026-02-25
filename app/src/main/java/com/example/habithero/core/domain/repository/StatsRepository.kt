package com.example.habithero.core.domain.repository

import com.example.habithero.core.domain.model.HabitProgress
import kotlinx.coroutines.flow.Flow

interface StatsRepository {

    fun getWeeklyStats(
        habitId: Int,
        startDate: String,
        endDate: String
    ): Flow<List<HabitProgress>>
}
