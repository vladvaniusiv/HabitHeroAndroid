package com.example.habithero.feature_settings.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.HabitHeroShapes
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.feature_settings.presentation.settings.SettingsUiState

@Composable
fun ChangePasswordSection(
    state: SettingsUiState,
    onPasswordChange: (String, String, String) -> Unit
) {
    var current by remember { mutableStateOf("") }
    var new by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    // Efecto secundario: si el cargando pasa a falso y no hay errores, asumimos éxito y limpiamos campos
    LaunchedEffect(state.isLoading) {
        if (!state.isLoading && state.currentPasswordError == null && state.newPasswordError == null && state.confirmPasswordError == null) {
            current = ""
            new = ""
            confirm = ""
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.change_passw_title), style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(4.dp))

        // Campo Contraseña Actual
        OutlinedTextField(
            value = current,
            onValueChange = { current = it },
            label = { Text(stringResource(R.string.current_passw)) },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            visualTransformation = PasswordVisualTransformation(),
            isError = state.currentPasswordError != null,
            supportingText = {
                state.currentPasswordError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            }
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Campo Nueva Contraseña
        OutlinedTextField(
            value = new,
            onValueChange = { new = it },
            label = { Text(stringResource(R.string.new_passw_label)) },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            visualTransformation = PasswordVisualTransformation(),
            isError = state.newPasswordError != null,
            supportingText = {
                state.newPasswordError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            }
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Campo Confirmar Contraseña
        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text(stringResource(R.string.confirm_passw_label)) },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            visualTransformation = PasswordVisualTransformation(),
            isError = state.confirmPasswordError != null,
            supportingText = {
                state.confirmPasswordError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            }
        )

        Button(onClick = { onPasswordChange(current, new, confirm) }) { Text(stringResource(R.string.save)) }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordSectionPreview() {
    HabitHeroTheme {
        ChangePasswordSection(
            state = SettingsUiState(),
            onPasswordChange = { _, _, _ -> }
        )
    }
}