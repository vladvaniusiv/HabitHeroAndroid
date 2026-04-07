package com.example.habithero.feature_home.presentation.home

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habithero.feature_home.presentation.screens.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    onNavigateToStats: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                HomeEvent.NavigateToStats -> onNavigateToStats()
                HomeEvent.NavigateToSettings -> onNavigateToSettings()
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}
