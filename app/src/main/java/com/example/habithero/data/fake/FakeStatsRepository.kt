package com.example.habithero.data.fake

import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.StatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeStatsRepository : StatsRepository {

    override fun getWeeklyStats(
        habitId: Int,
        startDate: String,
        endDate: String
    ): Flow<List<HabitProgress>> {
        return flowOf(emptyList())
    }
}
