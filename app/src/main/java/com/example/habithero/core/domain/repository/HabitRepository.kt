package com.example.habithero.core.domain.repository

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.model.HabitProgress
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun createHabit(habit: Habit)

    fun getHabitsForUser(userId: Int): Flow<List<Habit>>

    suspend fun toggleHabitProgress(progress: HabitProgress)
}
