package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.data.local.datasource.HabitLocalDataSource
import com.example.habithero.data.local.datastore.SessionDataStore
import com.example.habithero.data.local.entity.HabitProgressEntity
import com.example.habithero.data.mapper.toDomain
import com.example.habithero.data.mapper.toDto
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.HabitRemoteDataSource
import com.example.habithero.data.remote.dto.ToggleHabitRequestDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getHabitsForUser(userId: Int): Flow<List<Habit>> {
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        return flow {
            val token = sessionDataStore.token.first() ?: ""
            if (token.isNotEmpty()) {
                try {
                    val remoteHabits = remote.getHabits(token)
                    local.saveHabits(remoteHabits.map { it.toEntity() })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            // Emitimos de forma reactiva cruzando tablas
            val habitsFlow = local.getHabits(userId)

            val mappedFlow = habitsFlow.flatMapLatest { entities ->
                if (entities.isEmpty()) return@flatMapLatest flowOf(emptyList<Habit>())

                // Generamos una lista de flows observando el progreso individual de cada hábito para hoy
                val flows = entities.map { entity ->
                    local.getProgressForDate(entity.id ?: 0, todayStr).map { progressList ->
                        val isCompletedToday = progressList.any { it.completed }
                        // Convertimos a modelo de dominio forzando 'active' según el progreso de hoy
                        entity.toDomain().copy(active = isCompletedToday)
                    }
                }
                combine(flows) { it.toList() }
            }

            emitAll(mappedFlow)
        }
    }

    override suspend fun toggleHabitProgress(progress: HabitProgress) {
        val token = sessionDataStore.token.first() ?: ""

        // 1. Persistencia Local Inmediata (Room) -> Solucionado con el operador Elvis (?: 0)
        local.saveSingleProgress(
            HabitProgressEntity(
                id = progress.id
                    ?: 0, // <--- CORREGIDO: Si es nulo, pasa un 0 (Autoincrement de Room)
                habitId = progress.habitId,
                date = progress.date,
                completed = progress.completed
            )
        )

        // 2. Enviar sincrónicamente al Backend (Spring Boot) en segundo plano
        if (token.isNotEmpty()) {
            try {
                remote.toggleHabit(
                    token = token,
                    habitId = progress.habitId, // Si tu API espera Int y habitId es Int?, usa: progress.habitId ?: 0
                    request = ToggleHabitRequestDto(
                        date = progress.date,
                        completed = progress.completed
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}