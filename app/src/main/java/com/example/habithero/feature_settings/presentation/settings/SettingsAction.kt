package com.example.habithero.feature_settings.presentation.settings

sealed interface SettingsAction {
    data class OnNameChanged(val name: String) : SettingsAction
    data class OnUserNameChanged(val userName: String) : SettingsAction
    data class OnEmailChanged(val email: String) : SettingsAction
    data class OnPasswordChange(val current: String, val new: String, val confirm: String) : SettingsAction
    data object OnAvatarClicked : SettingsAction
    data object OnBackClicked : SettingsAction
    data object OnNavigateHome : SettingsAction
    data object OnNavigateStats : SettingsAction
}
