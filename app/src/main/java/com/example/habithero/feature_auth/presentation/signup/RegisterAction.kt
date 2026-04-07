package com.example.habithero.feature_auth.presentation.signup

sealed interface RegisterAction {
    data class OnNameChanged(val value: String) : RegisterAction
    data class OnEmailChanged(val value: String) : RegisterAction
    data class OnPasswordChanged(val value: String) : RegisterAction
    data class OnConfirmPasswordChanged(val value: String) : RegisterAction
    data class OnTermsAccepted(val value: Boolean) : RegisterAction

    data object OnSignUpClicked : RegisterAction
    data object OnBackToLoginClicked : RegisterAction
}