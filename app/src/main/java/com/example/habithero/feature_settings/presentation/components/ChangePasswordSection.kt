package com.example.habithero.feature_settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.core.designsystem.HabitHeroShapes
import com.example.habithero.core.designsystem.HabitHeroTheme

@Composable
fun ChangePasswordSection(
    onPasswordChange: (String, String, String) -> Unit
) {
    var current by remember { mutableStateOf("") }
    var new by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Cambiar contraseña", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = current,
            onValueChange = { current = it },
            label = { Text("Contraseña actual") },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = new,
            onValueChange = { new = it },
            label = { Text("Nueva contraseña") },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text("Confirma la contraseña") },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = { onPasswordChange(current, new, confirm) }) { Text("Guardar") }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordSectionPreview() {
    HabitHeroTheme {
        ChangePasswordSection(
            onPasswordChange = { _, _, _ -> }
        )
    }
}