package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.repository.StatsRepository
import com.example.habithero.data.local.datasource.StatsLocalDataSource
import com.example.habithero.data.local.datastore.SessionDataStore
import com.example.habithero.data.mapper.toDomain
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.StatsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class StatsRepositoryImpl(
    private val local: StatsLocalDataSource,
    private val remote: StatsRemoteDataSource,
    private val sessionDataStore: SessionDataStore
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

        try {
            val token = sessionDataStore.token.first() ?: ""

            if (token.isNotEmpty()) {
                // 3. Obtener del servidor usando el token dinámico
                val remoteStats = remote.getWeeklyStats(token, habitId, startDate, endDate)

                // 4. Actualizar la caché de Room local
                local.saveWeeklyStats(remoteStats.map { it.toEntity() })

                // 5. Emitir los nuevos datos remotos procesados
                emit(remoteStats.map { it.toDomain() })
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
