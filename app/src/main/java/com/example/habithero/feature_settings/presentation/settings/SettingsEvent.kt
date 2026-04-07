package com.example.habithero.feature_settings.presentation.settings

sealed interface SettingsEvent {
    data object NavigateBack : SettingsEvent
    data object NavigateToHome : SettingsEvent
    data object NavigateToStats : SettingsEvent
    data object ShowAvatarPicker : SettingsEvent
    data class ShowMessage(val message: String) : SettingsEvent
}
