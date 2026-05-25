package com.example.habithero.feature_settings.presentation.settings

data class SettingsUiState(
    val name: String = "",
    val userName: String = "",
    val email: String = "",
    val avatar: ByteArray? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userNameError: String? = null,
    val emailError: String? = null,
    val currentPasswordError: String? = null,
    val newPasswordError: String? = null,
    val confirmPasswordError: String? = null
)
