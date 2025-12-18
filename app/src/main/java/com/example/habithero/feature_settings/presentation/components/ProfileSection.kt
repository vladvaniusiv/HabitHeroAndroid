package com.example.habithero.feature_settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.core.designsystem.HabitHeroShapes
import com.example.habithero.core.designsystem.HabitHeroTheme

private fun isNameAvailable(name: String): Boolean {
    // TODO: consultar base de datos o API para verificar si el nombre ya existe
    // return false si el nombre está ocupado
    return true
}

private fun isUserNameAvailable(userName: String): Boolean {
    // TODO: consultar base de datos o API para verificar si el nombre de usuario ya existe
    // return false si el nombre de usuario está ocupado
    return true
}

private fun isEmailAvailable(email: String): Boolean {
    // TODO: consultar base de datos o API para verificar si el correo ya existe
    // return false si el correo está ocupado
    return true
}

@Composable
fun ProfileSection(
    initialName: String,
    initialUserName: String,
    initialEmail: String,
    onNameChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var userName by remember { mutableStateOf(initialUserName) }
    var email by remember { mutableStateOf(initialEmail) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var userNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Configuración del perfil", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))
        Text("Nombre", style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                onNameChange(it)
                // Validación
                nameError = if (!isNameAvailable(it)) "Este nombre ya está en uso" else null
            },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            isError = nameError != null
        )
        if (nameError != null) {
            Text(nameError!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Nombre de usuario", style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = userName,
            onValueChange = {
                userName = it
                onUserNameChange(it)
                // Validación
                userNameError = if (!isUserNameAvailable(it)) "Este nombre de usuario ya está en uso" else null
            },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            isError = userNameError != null
        )
        if (userNameError != null) {
            Text(userNameError!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Correo electrónico", style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                onEmailChange(it)
                // Validación
                emailError = if (!isEmailAvailable(it)) "Este correo ya está registrado" else null
            },
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            isError = emailError != null
        )
        if (emailError != null) {
            Text(emailError!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionPreview() {
    HabitHeroTheme {
        ProfileSection(
            initialName = "Vlad Vaniusiv",
            initialUserName = "@vladvan",
            initialEmail = "vladvaniusiv@gmail.com",
            onNameChange = {},
            onUserNameChange = {},
            onEmailChange = {}
        )
    }
}
