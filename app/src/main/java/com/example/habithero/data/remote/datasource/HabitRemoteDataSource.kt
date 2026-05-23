package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.HabitDto

class HabitRemoteDataSource(
    private val api: HabitHeroApi
) {

    suspend fun getHabits(token: String): List<HabitDto> {
        return api.getHabits("Bearer $token")
    }

    suspend fun createHabit(token: String, habit: HabitDto) {
        api.createHabit("Bearer $token", habit)
    }
}
