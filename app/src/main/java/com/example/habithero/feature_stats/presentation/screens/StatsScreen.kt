package com.example.habithero.feature_stats.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.core.ui.Routes
import com.example.habithero.core.ui.components.BottomBar
import com.example.habithero.core.ui.components.TopBar
import com.example.habithero.feature_stats.presentation.components.WeeklyChart
import com.example.habithero.feature_stats.presentation.stats.StatsAction
import com.example.habithero.feature_stats.presentation.stats.StatsUiState

@Composable
fun StatsScreen(
    uiState: StatsUiState,
    onAction: (StatsAction) -> Unit
) {
    val weeklyProgress = uiState.weeklyProgress

    Scaffold(
        topBar = { TopBar(titleRes = R.string.stats, onBackClick = { onAction(StatsAction.OnBackClicked) }) },

        bottomBar = {
            BottomBar(
                currentRoute = Routes.STATS,
                onNavigate = { route ->
                    when (route) {
                        Routes.HOME -> onAction(StatsAction.OnHomeClicked)
                        Routes.SETTINGS -> onAction(StatsAction.OnSettingsClicked)
                        Routes.STATS -> {}
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Icono de estadísticas centrado arriba
            Image(
                painter = painterResource(R.drawable.statistics_logo),
                contentDescription = "Estadísticas",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Gráfico semanal
            WeeklyChart(weeklyProgress = weeklyProgress)

            Spacer(modifier = Modifier.height(16.dp))

            // Logros
            Text(
                text = stringResource(R.string.achievements),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.achievement_1),
                    contentDescription = "Logro 1",
                    modifier = Modifier.size(64.dp)
                )
                Image(
                    painter = painterResource(R.drawable.achievement_2),
                    contentDescription = "Logro 2",
                    modifier = Modifier.size(64.dp)
                )
                Image(
                    painter = painterResource(R.drawable.achievement_3),
                    contentDescription = "Logro 3",
                    modifier = Modifier.size(64.dp)
                )
                Image(
                    painter = painterResource(R.drawable.achievement_4),
                    contentDescription = "Logro 4",
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatsScreenPreview() {
    HabitHeroTheme {
        StatsScreen(
            uiState = StatsUiState(),
            onAction = {}
        )
    }
}
