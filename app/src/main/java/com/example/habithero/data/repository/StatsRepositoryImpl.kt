package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.StatsRepository
import com.example.habithero.data.local.datasource.StatsLocalDataSource
import com.example.habithero.data.mapper.toDomain
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.StatsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class StatsRepositoryImpl(
    private val local: StatsLocalDataSource,
    private val remote: StatsRemoteDataSource
) : StatsRepository {

    override fun getWeeklyStats(
        habitId: Int,
        startDate: String,
        endDate: String
    ): Flow<List<HabitProgress>> = flow {

        // 1. Emitir datos locales
        emitAll(
            local.getWeeklyStats(habitId, startDate, endDate)
                .map { list -> list.map { it.toDomain() } }
        )

        // 2. Obtener remoto
        val remoteStats = remote.getWeeklyStats(habitId, startDate, endDate)

        // 3. Guardar en local
        local.saveWeeklyStats(remoteStats.map { it.toEntity() })

        // 4. Emitir datos remotos
        emit(remoteStats.map { it.toDomain() })
    }
}
