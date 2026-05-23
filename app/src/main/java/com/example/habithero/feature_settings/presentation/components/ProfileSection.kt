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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.HabitHeroShapes

@Composable
fun ProfileSection(
    initialName: String,
    initialUserName: String,
    initialEmail: String,
    userNameError: String?,
    emailError: String?,
    onNameChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(stringResource(R.string.account_config_title), style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.name), style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = initialName,
            onValueChange = onNameChange,
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.username), style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = initialUserName,
            onValueChange = onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            isError = userNameError != null
        )
        if (userNameError != null) {
            Text(userNameError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.email), style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(
            value = initialEmail,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.extraLarge,
            isError = emailError != null
        )
        if (emailError != null) {
            Text(emailError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = userNameError == null && emailError == null
        ) {
            Text(stringResource(R.string.save))
        }
    }
}