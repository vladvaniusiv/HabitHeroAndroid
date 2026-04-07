package com.example.habithero.feature_auth.presentation.signup

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val acceptedTerms: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)