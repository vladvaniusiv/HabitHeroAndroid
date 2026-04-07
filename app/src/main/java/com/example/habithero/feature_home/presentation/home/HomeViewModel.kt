package com.example.habithero.feature_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.core.domain.usecase.CreateHabitUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class HomeViewModel(
    private val habitRepository: HabitRepository,
    private val createHabitUseCase: CreateHabitUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<HomeEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadHabits()
    }

    private fun loadHabits() {
        // datos fake para que compile
        _uiState.value = HomeUiState(
            habits = listOf(
                "Meditar 10 minutos" to true,
                "Leer 20 páginas" to true,
                "Beber 2L de agua" to true,
                "Caminar 30 minutos" to false
            )
        )
    }

    fun onAction(action: HomeAction) {
        when (action) {

            is HomeAction.OnToggleHabit -> {
                _uiState.value = _uiState.value.copy(
                    habits = _uiState.value.habits.map {
                        if (it.first == action.habitName) action.habitName to action.completed else it
                    }
                )
            }

            HomeAction.OnRefresh -> loadHabits()

            is HomeAction.OnHabitClicked -> {
                viewModelScope.launch { _events.send(HomeEvent.NavigateToStats) }
            }

            HomeAction.OnSettingsClicked -> {
                viewModelScope.launch { _events.send(HomeEvent.NavigateToSettings) }
            }

            HomeAction.OnStatsClicked -> {
                viewModelScope.launch { _events.send(HomeEvent.NavigateToStats) }
            }
        }
    }
}
