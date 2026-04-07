package com.example.habithero.feature_auth.presentation.signup

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habithero.feature_auth.presentation.screens.SignUpScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoute(
    onNavigateToHome: () -> Unit,
    onNavigateBackToLogin: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                RegisterEvent.NavigateToHome -> onNavigateToHome()
                RegisterEvent.NavigateBackToLogin -> onNavigateBackToLogin()
                is RegisterEvent.ShowError -> {
                    // Snackbar / Toast
                }
            }
        }
    }

    SignUpScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}