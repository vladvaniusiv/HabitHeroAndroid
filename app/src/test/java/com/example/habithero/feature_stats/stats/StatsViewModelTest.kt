package com.example.habithero.feature_stats.stats

import app.cash.turbine.test
import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.usecase.GetWeeklyStatsUseCase
import com.example.habithero.feature_stats.presentation.stats.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StatsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getWeeklyStatsUseCase: GetWeeklyStatsUseCase
    private lateinit var viewModel: StatsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        getWeeklyStatsUseCase = mockk()

        viewModel = StatsViewModel(getWeeklyStatsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun estadoInicial_muestra_cargando() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)

        getWeeklyStatsUseCase = mockk()
        val viewModelLocal = StatsViewModel(getWeeklyStatsUseCase, autoLoad = false)

        assertTrue(viewModelLocal.uiState.value.isLoading)
    }

    @Test
    fun loadStats_con_lista_vacia_genera_datos_fake() = runTest {
        coEvery { getWeeklyStatsUseCase(any(), any(), any()) } returns flow {
            emit(emptyList())
        }

        viewModel.onAction(StatsAction.OnLoadStats)

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertTrue(state.weeklyProgress.isNotEmpty())
        assertEquals(5, state.weeklyProgress.size)
    }

    @Test
    fun loadStats_con_datos_reales_actualiza_weeklyProgress() = runTest {
        val fakeData = listOf(
            HabitProgress(1, 1, "2024-06-01", true),
            HabitProgress(2, 1, "2024-06-02", false)
        )

        coEvery { getWeeklyStatsUseCase(any(), any(), any()) } returns flow {
            emit(fakeData)
        }

        viewModel.onAction(StatsAction.OnLoadStats)

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals(1, state.weeklyProgress.size)
        assertEquals(0.5f, state.weeklyProgress.first())
    }

    @Test
    fun loadStats_con_error_muestra_mensaje_de_error() = runTest {
        coEvery { getWeeklyStatsUseCase(any(), any(), any()) } returns flow {
            throw RuntimeException("Error")
        }

        viewModel.onAction(StatsAction.OnLoadStats)

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals("Error al cargar estadísticas", state.errorMessage)
    }

    @Test
    fun onBackClicked_emite_navegacion_atras() = runTest {
        viewModel.events.test {
            viewModel.onAction(StatsAction.OnBackClicked)

            advanceUntilIdle()

            assertEquals(StatsEvent.NavigateBack, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onHomeClicked_emite_navegacion_home() = runTest {
        viewModel.events.test {
            viewModel.onAction(StatsAction.OnHomeClicked)

            advanceUntilIdle()

            assertEquals(StatsEvent.NavigateToHome, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onSettingsClicked_emite_navegacion_settings() = runTest {
        viewModel.events.test {
            viewModel.onAction(StatsAction.OnSettingsClicked)

            advanceUntilIdle()

            assertEquals(StatsEvent.NavigateToSettings, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
