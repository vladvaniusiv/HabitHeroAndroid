package com.example.habithero.feature_home.home

import app.cash.turbine.test
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.core.domain.usecase.CreateHabitUseCase
import com.example.habithero.feature_home.presentation.home.*
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var habitRepository: HabitRepository
    private lateinit var createHabitUseCase: CreateHabitUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        habitRepository = mockk(relaxed = true)
        createHabitUseCase = mockk(relaxed = true)

        viewModel = HomeViewModel(habitRepository, createHabitUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun estadoInicial_contiene_habitos_por_defecto() = runTest {
        val habits = viewModel.uiState.value.habits
        assertEquals(4, habits.size)
    }

    @Test
    fun onToggleHabit_actualiza_el_estado() = runTest {
        viewModel.onAction(HomeAction.OnToggleHabit("Meditar 10 minutos", false))

        val updated = viewModel.uiState.value.habits.first { it.first == "Meditar 10 minutos" }
        assertFalse(updated.second)
    }

    @Test
    fun onRefresh_restaurar_habitos_por_defecto() = runTest {
        // Cambiamos algo
        viewModel.onAction(HomeAction.OnToggleHabit("Meditar 10 minutos", false))

        // Refrescamos
        viewModel.onAction(HomeAction.OnRefresh)

        val habits = viewModel.uiState.value.habits
        assertEquals(4, habits.size)
        assertTrue(habits.first { it.first == "Meditar 10 minutos" }.second)
    }

    @Test
    fun onHabitClicked_emite_navegacion_a_stats() = runTest {
        viewModel.events.test {
            viewModel.onAction(HomeAction.OnHabitClicked("Meditar 10 minutos"))

            advanceUntilIdle()

            assertEquals(HomeEvent.NavigateToStats, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onSettingsClicked_emite_navegacion_a_settings() = runTest {
        viewModel.events.test {
            viewModel.onAction(HomeAction.OnSettingsClicked)

            advanceUntilIdle()

            assertEquals(HomeEvent.NavigateToSettings, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onStatsClicked_emite_navegacion_a_stats() = runTest {
        viewModel.events.test {
            viewModel.onAction(HomeAction.OnStatsClicked)

            advanceUntilIdle()

            assertEquals(HomeEvent.NavigateToStats, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
