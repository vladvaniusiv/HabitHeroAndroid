package com.example.habithero.feature_stats.presentation.stats
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.habithero.feature_stats.presentation.screens.StatsScreen
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatsRoute(
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: StatsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                StatsEvent.NavigateBack -> onNavigateBack()
                StatsEvent.NavigateToHome -> onNavigateToHome()
                StatsEvent.NavigateToSettings -> onNavigateToSettings()
            }
        }
    }

    StatsScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}