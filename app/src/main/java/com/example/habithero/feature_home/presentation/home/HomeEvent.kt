package com.example.habithero.feature_home.presentation.home
sealed interface HomeEvent {
    data object NavigateToStats : HomeEvent
    data object NavigateToSettings : HomeEvent
}