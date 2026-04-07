package com.example.habithero.feature_stats.presentation.stats
data class StatsUiState(
    val weeklyProgress: List<Float> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)