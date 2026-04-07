package com.example.habithero.feature_settings.presentation.settings

data class SettingsUiState(
    val name: String = "",
    val userName: String = "",
    val email: String = "",
    val avatar: ByteArray? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
