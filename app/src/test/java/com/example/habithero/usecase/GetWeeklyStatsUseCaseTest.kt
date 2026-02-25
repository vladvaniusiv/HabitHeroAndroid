package com.example.habithero.usecase

import com.example.habithero.core.domain.repository.StatsRepository
import com.example.habithero.core.domain.usecase.GetWeeklyStatsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertEquals

class GetWeeklyStatsUseCaseTest {

    private val repository = mockk<StatsRepository>()
    private val useCase = GetWeeklyStatsUseCase(repository)

    @Test
    fun falla_si_habitId_es_invalida() = runTest {
        assertFailsWith<IllegalArgumentException> {
            useCase(0, "2026-02-20", "2026-02-28")
        }
    }

    @Test
    fun falla_si_fechas_vacias() = runTest {
        assertFailsWith<IllegalArgumentException> {
            useCase(1, "", "")
        }
    }

    @Test
    fun retorna_flujo_del_repo_si_todo_ok() = runTest {
        val expected = emptyList<com.example.habithero.core.domain.model.HabitProgress>()

        every {
            repository.getWeeklyStats(1, "2026-02-20", "2026-02-28")
        } returns flowOf(expected)

        val result = useCase(1, "2026-02-20", "2026-02-28")

        result.collect { list ->
            assertEquals(expected, list)
        }
    }
}
