package com.example.habithero.core.domain.usecase

import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.StatsRepository
import kotlinx.coroutines.flow.Flow

class GetWeeklyStatsUseCase(
    private val repository: StatsRepository
) {

    operator fun invoke(
        habitId: Int,
        startDate: String,
        endDate: String
    ): Flow<List<HabitProgress>> {

        if (habitId <= 0) {
            throw IllegalArgumentException("El habitId no es válido")
        }
        if (startDate.isBlank() || endDate.isBlank()) {
            throw IllegalArgumentException("Las fechas no pueden estar vacías")
        }

        return repository.getWeeklyStats(habitId, startDate, endDate)
    }
}
