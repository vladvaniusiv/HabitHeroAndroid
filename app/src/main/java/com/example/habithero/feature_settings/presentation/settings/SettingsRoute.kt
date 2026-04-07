package com.example.habithero.feature_settings.presentation.settings

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habithero.feature_settings.presentation.screens.SettingsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRoute(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToStats: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                SettingsEvent.NavigateBack -> onNavigateBack()
                SettingsEvent.NavigateToHome -> onNavigateToHome()
                SettingsEvent.NavigateToStats -> onNavigateToStats()
                is SettingsEvent.ShowMessage -> {
                    // TODO: Snackbar o Toast
                }
                SettingsEvent.ShowAvatarPicker -> {
                    // TODO: abrir selector de imagen
                }
            }
        }
    }

    SettingsScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}
