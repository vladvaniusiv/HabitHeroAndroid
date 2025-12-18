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

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToStats: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    var name by remember { mutableStateOf("Vlad Vaniusiv") }
    var userName by remember { mutableStateOf("@vladvan") }
    var email by remember { mutableStateOf("vlad@gmail.com") }

    Scaffold(
        topBar = { TopBar(titleRes = R.string.settings, onBackClick = onNavigateBack) },
          bottomBar = {
            BottomBar(
                currentRoute = Routes.SETTINGS, // ruta correcta
                onNavigate = { route ->
                    when (route) {
                        Routes.HOME -> onNavigateToHome()
                        Routes.STATS -> onNavigateToStats()
                        Routes.SETTINGS -> { /* ya estamos en settings */ }
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
                initialName = name,
                initialUserName = userName,
                initialEmail = email,
                onNameChange = { name = it },
                onUserNameChange = { userName = it },
                onEmailChange = { email = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            ChangePasswordSection(
                onPasswordChange = { current, new, confirm ->
                    // TODO: lógica de cambio de contraseña
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
            onNavigateBack = {},
            onNavigateToStats = {},
            onNavigateToHome = {}
        )
    }
}
