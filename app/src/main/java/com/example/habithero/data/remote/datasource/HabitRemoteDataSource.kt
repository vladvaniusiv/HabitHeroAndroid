package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.HabitDto

class HabitRemoteDataSource(
    private val api: HabitHeroApi
) {

    suspend fun getHabits(userId: Int): List<HabitDto> {
        return api.getHabits(userId)
    }

    suspend fun createHabit(habit: HabitDto) {
        api.createHabit(habit)
    }
}
