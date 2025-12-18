package com.example.habithero.feature_auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.core.designsystem.BrandGreen
import com.example.habithero.core.designsystem.HabitHeroShapes
import com.example.habithero.core.designsystem.HabitHeroTheme

@Composable
fun ResetPasswordDialog(
    resetEmail: String,
    onEmailChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(
                onClick = { onConfirm() },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
            ) {
                Text("Resetear la contraseña", color = MaterialTheme.colorScheme.onPrimary)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancelar")
            }
        },
        title = { Text("Recuperar contraseña") },
        text = {
            Column {
                Text("Introduce tu correo electrónico para resetear la contraseña:")
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = resetEmail,
                    onValueChange = { onEmailChange(it) },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = HabitHeroShapes.extraLarge
                )
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun ResetPasswordDialogPreview() {
    HabitHeroTheme {
        ResetPasswordDialog(
            resetEmail = "",
            onEmailChange = {},
            onDismiss = {},
            onConfirm = {}
        )
    }
}