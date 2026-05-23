package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.data.local.datasource.HabitLocalDataSource
import com.example.habithero.data.local.datastore.SessionDataStore
import com.example.habithero.data.mapper.toDomain
import com.example.habithero.data.mapper.toDto
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.HabitRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HabitRepositoryImpl(
    private val local: HabitLocalDataSource,
    private val remote: HabitRemoteDataSource,
    private val sessionDataStore: SessionDataStore
) : HabitRepository {

    override suspend fun createHabit(habit: Habit) {
        val token = sessionDataStore.token.first() ?: ""
        if (token.isNotEmpty()) {
            remote.createHabit(token, habit.toDto())
        }
        local.saveHabits(listOf(habit.toEntity()))
    }

    override fun getHabitsForUser(userId: Int): Flow<List<Habit>> = flow {

        emitAll(
            local.getHabits(userId)
                .map { list -> list.map { it.toDomain() } }
        )

        try {
            val token = sessionDataStore.token.first() ?: ""
            if (token.isNotEmpty()) {
                val remoteHabits = remote.getHabits(token)
                local.saveHabits(remoteHabits.map { it.toEntity() })
                emit(remoteHabits.map { it.toDomain() })
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun toggleHabitProgress(progress: HabitProgress) {
        // No hay API real → se podría guardar en local
        // Lo dejamos vacío como en tu FakeRepository
    }
}
