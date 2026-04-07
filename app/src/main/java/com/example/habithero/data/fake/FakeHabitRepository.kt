package com.example.habithero.data.fake

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeHabitRepository : HabitRepository {

    private val habits = MutableStateFlow<List<Habit>>(emptyList())

    override suspend fun createHabit(habit: Habit) {
        habits.value = habits.value + habit.copy(id = habits.value.size + 1)
    }

    override fun getHabitsForUser(userId: Int): Flow<List<Habit>> {
        return habits
    }

    override suspend fun toggleHabitProgress(progress: HabitProgress) {
    }
}
