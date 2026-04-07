package com.example.habithero.feature_auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habithero.feature_auth.presentation.screens.LoginScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoute(
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                LoginEvent.NavigateToHome -> onNavigateToHome()
                is LoginEvent.ShowError -> {
                    // TODO Snackbar, Toast, etc.
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        onSignUpClick = onNavigateToSignUp
    )
}
