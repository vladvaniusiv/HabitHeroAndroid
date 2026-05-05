package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.HabitProgressDao
import com.example.habithero.data.local.entity.HabitProgressEntity
import kotlinx.coroutines.flow.Flow

class StatsLocalDataSource(
    private val dao: HabitProgressDao
) {

    fun getWeeklyStats(
        habitId: Int,
        start: String,
        end: String
    ): Flow<List<HabitProgressEntity>> {
        return dao.getProgress(habitId, start, end)
    }

    suspend fun saveWeeklyStats(list: List<HabitProgressEntity>) {
        dao.insertProgress(list)
    }
}
