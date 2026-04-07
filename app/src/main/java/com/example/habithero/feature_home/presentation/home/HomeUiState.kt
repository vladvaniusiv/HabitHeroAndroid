package com.example.habithero.feature_home.presentation.home
import com.example.habithero.core.domain.model.Habit

data class HomeUiState(
    val habits: List<Pair<String, Boolean>> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)