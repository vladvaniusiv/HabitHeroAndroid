package com.example.habithero.feature_home.presentation.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.remote.creation.second
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.core.ui.components.BottomBar
import com.example.habithero.core.ui.components.TopBar
import com.example.habithero.feature_home.presentation.components.HabitList
import com.example.habithero.feature_home.presentation.components.ProgressSummary
import com.example.habithero.feature_home.presentation.home.HomeAction
import com.example.habithero.feature_home.presentation.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit
){
    val habits = uiState.habits
    val progress = if (habits.isNotEmpty()) {
        habits.count { it.second }.toFloat() / habits.size
    } else 0f
    var showDialog by remember { mutableStateOf(false) }
    var newHabitTitle by remember { mutableStateOf("") }
    val context = LocalContext.current
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            // Convertimos la URI del archivo a un ByteArray para poder enviarlo al servidor
            val inputStream = context.contentResolver.openInputStream(it)
            val bytes = inputStream?.readBytes()
            inputStream?.close()

            if (bytes != null) {
                onAction(HomeAction.OnAvatarSelected(bytes))
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Nuevo Hábito") },
            text = {
                OutlinedTextField(
                    value = newHabitTitle,
                    onValueChange = { newHabitTitle = it },
                    label = { Text("¿Qué hábito quieres desarrollar?") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newHabitTitle.isNotBlank()) {
                            onAction(HomeAction.OnCreateHabitSubmitted(newHabitTitle))
                            newHabitTitle = ""
                            showDialog = false
                        }
                    }
                ) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopBar(titleRes = R.string.home)
        },
        bottomBar = {
            BottomBar(
                currentRoute = "home",
                onNavigate = { route ->
                    when (route) {
                        "stats" -> onAction(HomeAction.OnStatsClicked)
                        "settings" -> onAction(HomeAction.OnSettingsClicked)
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
            // Avatar centrado con opción de modificar
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = uiState.name.ifBlank { "Usuario Hero" },
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = if (uiState.username.isBlank()) "" else "@${uiState.username}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de hábitos
            HabitList(
                habits = habits,
                onHabitChecked = { habitName, checked ->
                    onAction(HomeAction.OnToggleHabit(habitName, checked))
                },
                onCreateHabit = {
                    showDialog = true
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Resumen de progreso
            ProgressSummary(progress = progress)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HabitHeroTheme {
        HomeScreen(
            uiState = HomeUiState(),onAction = {}
        )
    }
}
