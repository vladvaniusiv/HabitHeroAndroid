package com.example.habithero.feature_auth.presentation.signup

sealed interface RegisterEvent {
    data object NavigateToHome : RegisterEvent
    data object NavigateBackToLogin : RegisterEvent
    data class ShowError(val message: String) : RegisterEvent
}