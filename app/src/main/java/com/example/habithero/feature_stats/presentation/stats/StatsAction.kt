package com.example.habithero.feature_stats.presentation.stats
sealed interface StatsAction {
    data object OnLoadStats : StatsAction
    data object OnBackClicked : StatsAction
    data object OnHomeClicked : StatsAction
    data object OnSettingsClicked : StatsAction
}