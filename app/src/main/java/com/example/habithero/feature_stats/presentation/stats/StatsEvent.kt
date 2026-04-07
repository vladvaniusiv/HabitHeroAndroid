package com.example.habithero.feature_stats.presentation.stats
sealed interface StatsEvent {
    data object NavigateBack : StatsEvent
    data object NavigateToHome : StatsEvent
    data object NavigateToSettings : StatsEvent
}