package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.data.local.datasource.HabitLocalDataSource
import com.example.habithero.data.mapper.toDomain
import com.example.habithero.data.mapper.toDto
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.HabitRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HabitRepositoryImpl(
    private val local: HabitLocalDataSource,
    private val remote: HabitRemoteDataSource
) : HabitRepository {

    override suspend fun createHabit(habit: Habit) {
        remote.createHabit(habit.toDto())
        local.saveHabits(listOf(habit.toEntity()))
    }

    override fun getHabitsForUser(userId: Int): Flow<List<Habit>> = flow {

        emitAll(
            local.getHabits(userId)
                .map { list -> list.map { it.toDomain() } }
        )

        // 2. Obtener remoto
        val remoteHabits = remote.getHabits(userId)

        // 3. Guardar en local
        local.saveHabits(remoteHabits.map { it.toEntity() })

        // 4. Emitir datos remotos
        emit(remoteHabits.map { it.toDomain() })
    }

    override suspend fun toggleHabitProgress(progress: HabitProgress) {
        // No hay API real → se podría guardar en local
        // Lo dejamos vacío como en tu FakeRepository
    }
}
