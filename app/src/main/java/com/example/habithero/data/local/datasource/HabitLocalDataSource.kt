package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.HabitDao
import com.example.habithero.data.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

class HabitLocalDataSource(
    private val dao: HabitDao
) {

    fun getHabits(userId: Int): Flow<List<HabitEntity>> {
        return dao.getHabits(userId)
    }

    suspend fun saveHabits(habits: List<HabitEntity>) {
        dao.insertHabits(habits)
    }
}
