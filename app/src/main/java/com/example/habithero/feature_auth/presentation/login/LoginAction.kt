package com.example.habithero.feature_auth.presentation.login

sealed interface LoginAction {
    data class OnEmailChanged(val value: String) : LoginAction
    data class OnPasswordChanged(val value: String) : LoginAction
    data object OnLoginClicked : LoginAction
}
