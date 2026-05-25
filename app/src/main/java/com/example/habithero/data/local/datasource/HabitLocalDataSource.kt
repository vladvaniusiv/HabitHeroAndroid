package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.HabitDao
import com.example.habithero.data.local.dao.HabitProgressDao
import com.example.habithero.data.local.entity.HabitEntity
import com.example.habithero.data.local.entity.HabitProgressEntity
import kotlinx.coroutines.flow.Flow

class HabitLocalDataSource(
    private val dao: HabitDao,
    private val progressDao: HabitProgressDao
) {

    fun getHabits(userId: Int): Flow<List<HabitEntity>> {
        return dao.getHabits(userId)
    }

    suspend fun saveHabits(habits: List<HabitEntity>) {
        dao.insertHabits(habits)
    }

    fun getProgressForDate(habitId: Int, date: String): Flow<List<HabitProgressEntity>> {
        return progressDao.getProgress(habitId, date, date)
    }

    suspend fun saveSingleProgress(progress: HabitProgressEntity) {
        progressDao.insertProgress(listOf(progress))
    }
}
