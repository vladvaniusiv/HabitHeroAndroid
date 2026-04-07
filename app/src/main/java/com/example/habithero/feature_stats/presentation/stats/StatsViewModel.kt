package com.example.habithero.feature_stats.presentation.stats
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.core.domain.usecase.GetWeeklyStatsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.WeekFields

@RequiresApi(Build.VERSION_CODES.O)
class StatsViewModel(
    private val getWeeklyStatsUseCase: GetWeeklyStatsUseCase,
    private val autoLoad: Boolean = true
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatsUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<StatsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        if (autoLoad) loadStats()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadStats() {
        viewModelScope.launch {
            try {
                val habitId = 1
                val startDate = "2024-01-01"
                val endDate = "2024-12-31"

                getWeeklyStatsUseCase(habitId, startDate, endDate)
                    .collect { progressList ->

                        val safeList =
                            if (progressList.isEmpty()) {
                                // Datos fake para 4 semanas
                                listOf(
                                    HabitProgress(1, habitId, "2024-06-01", true),
                                    HabitProgress(2, habitId, "2024-06-02", true),
                                    HabitProgress(3, habitId, "2024-06-03", true),
                                    HabitProgress(4, habitId, "2024-06-04", true),
                                    HabitProgress(5, habitId, "2024-06-05", false),
                                    HabitProgress(6, habitId, "2024-06-06", false),
                                    HabitProgress(7, habitId, "2024-06-07", false),
                                    HabitProgress(8, habitId, "2024-06-08", true),
                                    HabitProgress(9, habitId, "2024-06-09", true),
                                    HabitProgress(10, habitId, "2024-06-10", true),
                                    HabitProgress(11, habitId, "2024-06-11", true),
                                    HabitProgress(12, habitId, "2024-06-12", true),
                                    HabitProgress(13, habitId, "2024-06-13", true),
                                    HabitProgress(14, habitId, "2024-06-14", false),
                                    HabitProgress(15, habitId, "2024-06-15", true),
                                    HabitProgress(16, habitId, "2024-06-16", true),
                                    HabitProgress(17, habitId, "2024-06-17", true),
                                    HabitProgress(18, habitId, "2024-06-18", false),
                                    HabitProgress(19, habitId, "2024-06-19", false),
                                    HabitProgress(20, habitId, "2024-06-20", false),
                                    HabitProgress(21, habitId, "2024-06-21", false),
                                    HabitProgress(22, habitId, "2024-06-22", true),
                                    HabitProgress(23, habitId, "2024-06-23", true),
                                    HabitProgress(24, habitId, "2024-06-24", true),
                                    HabitProgress(25, habitId, "2024-06-25", true),
                                    HabitProgress(26, habitId, "2024-06-26", true),
                                    HabitProgress(27, habitId, "2024-06-27", true),
                                    HabitProgress(28, habitId, "2024-06-28", true)
                                )
                            } else {
                                progressList
                            }

                        val grouped = safeList.groupBy { progress ->
                            val date = LocalDate.parse(progress.date)
                            val week = date.get(WeekFields.ISO.weekOfWeekBasedYear())
                            val year = date.year
                            "$year-W$week"
                        }

                        val weeklyFloats = grouped.values.map { weekEntries ->
                            val total = weekEntries.size
                            val completed = weekEntries.count { it.completed }
                            completed.toFloat() / total
                        }

                        _uiState.value = StatsUiState(
                            weeklyProgress = weeklyFloats,
                            isLoading = false
                        )
                    }

            } catch (e: Exception) {
                _uiState.value = StatsUiState(
                    weeklyProgress = emptyList(),
                    isLoading = false,
                    errorMessage = "Error al cargar estadísticas"
                )
            }
        }
    }

    fun onAction(action: StatsAction) {
        when (action) {

            StatsAction.OnLoadStats -> loadStats()

            StatsAction.OnBackClicked ->
                viewModelScope.launch { _events.send(StatsEvent.NavigateBack) }

            StatsAction.OnHomeClicked ->
                viewModelScope.launch { _events.send(StatsEvent.NavigateToHome) }

            StatsAction.OnSettingsClicked ->
                viewModelScope.launch { _events.send(StatsEvent.NavigateToSettings) }
        }
    }
}