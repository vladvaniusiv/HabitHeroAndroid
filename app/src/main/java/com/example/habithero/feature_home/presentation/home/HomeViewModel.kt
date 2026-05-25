package com.example.habithero.feature_home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.core.domain.usecase.CreateHabitUseCase
import com.example.habithero.data.local.datasource.UserLocalDataSource
import com.example.habithero.data.local.datastore.SessionDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val habitRepository: HabitRepository,
    private val createHabitUseCase: CreateHabitUseCase,
    private val sessionDataStore: SessionDataStore,
    private val userLocalDataSource: UserLocalDataSource
) : ViewModel() {

    private val _events = Channel<HomeEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var currentUserId: Int? = null

    // El único uiState que debe existir (Reactivo)
    val uiState: StateFlow<HomeUiState> = sessionDataStore.userId
        .onEach { id -> currentUserId = id }
        .flatMapLatest { userId ->
            if (userId != null) {
                combine(
                    habitRepository.getHabitsForUser(userId),
                    userLocalDataSource.getUser()
                ) { habitsList, userEntity ->
                    HomeUiState(
                        habits = habitsList.map { it.title to false },
                        name = userEntity?.name ?: "Usuario",
                        username = userEntity?.username ?: "sin_username",
                        isLoading = false
                    )
                }
                    .catch { e ->
                        emit(HomeUiState(errorMessage = e.message, isLoading = false))
                    }
            } else {
                flowOf(HomeUiState(habits = emptyList(), isLoading = false))
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(isLoading = true)
        )

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCreateHabitSubmitted -> {
                val userId = currentUserId ?: return
                viewModelScope.launch {
                    try {
                        val newHabit = Habit(
                            id = 0,
                            title = action.title,
                            description = "Hábito creado desde el móvil",
                            userId = userId
                        )
                        createHabitUseCase(newHabit)
                        // Al ser reactivo, la base de datos notificará sola, no necesitas llamar a loadHabits()
                    } catch (e: Exception) {
                        println("Error al crear hábito: ${e.message}")
                    }
                }
            }
            is HomeAction.OnToggleHabit -> {
                // Si necesitas modificar el estado del toggle localmente sin persistir aún en la BD,
                // idealmente deberías manejar un MutableStateFlow interno o delegarlo al repositorio/caso de uso.
            }
            is HomeAction.OnAvatarSelected -> {
                viewModelScope.launch {
                    println("Imagen capturada con éxito. Tamaño en bytes: ${action.imageBytes.size}")
                }
            }
            HomeAction.OnRefresh -> { /* Se refresca automáticamente al cambiar la BD */ }
            is HomeAction.OnHabitClicked -> { viewModelScope.launch { _events.send(HomeEvent.NavigateToStats) } }
            HomeAction.OnSettingsClicked -> { viewModelScope.launch { _events.send(HomeEvent.NavigateToSettings) } }
            HomeAction.OnStatsClicked -> { viewModelScope.launch { _events.send(HomeEvent.NavigateToStats) } }
        }
    }
}