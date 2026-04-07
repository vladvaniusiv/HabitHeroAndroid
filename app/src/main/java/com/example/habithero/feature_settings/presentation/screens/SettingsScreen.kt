package com.example.habithero.feature_settings.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.core.ui.Routes
import com.example.habithero.core.ui.components.BottomBar
import com.example.habithero.core.ui.components.TopBar
import com.example.habithero.feature_settings.presentation.ChangePasswordSection
import com.example.habithero.feature_settings.presentation.ProfileSection
import com.example.habithero.feature_settings.presentation.settings.SettingsAction
import com.example.habithero.feature_settings.presentation.settings.SettingsUiState

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onAction: (SettingsAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                titleRes = R.string.settings,
                onBackClick = { onAction(SettingsAction.OnBackClicked) }
            )
        },
        bottomBar = {
            BottomBar(
                currentRoute = Routes.SETTINGS,
                onNavigate = { route ->
                    when (route) {
                        Routes.HOME -> onAction(SettingsAction.OnNavigateHome)
                        Routes.STATS -> onAction(SettingsAction.OnNavigateStats)
                        Routes.SETTINGS -> {}
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            ProfileSection(
                initialName = uiState.name,
                initialUserName = uiState.userName,
                initialEmail = uiState.email,
                onNameChange = { onAction(SettingsAction.OnNameChanged(it)) },
                onUserNameChange = { onAction(SettingsAction.OnUserNameChanged(it)) },
                onEmailChange = { onAction(SettingsAction.OnEmailChanged(it)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            ChangePasswordSection(
                onPasswordChange = { current, new, confirm ->
                    onAction(SettingsAction.OnPasswordChange(current, new, confirm))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    HabitHeroTheme {
        SettingsScreen(
            uiState = SettingsUiState(
                name = "Vlad Vaniusiv",
                userName = "@vladvan",
                email = "vlad@gmail.com"
            ),
            onAction = {}
        )
    }
}
