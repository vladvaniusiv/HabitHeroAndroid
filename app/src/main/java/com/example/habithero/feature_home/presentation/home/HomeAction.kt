package com.example.habithero.feature_home.presentation.home
sealed interface HomeAction {
    data class OnToggleHabit(val habitId: Int, val completed: Boolean) : HomeAction
    data object OnRefresh : HomeAction
    data class OnHabitClicked(val habitName: String) : HomeAction
    data object OnSettingsClicked : HomeAction
    data object OnStatsClicked : HomeAction
    data class OnAvatarSelected(val imageBytes: ByteArray) : HomeAction
    data class OnCreateHabitSubmitted(val title: String) : HomeAction
}