package com.example.habithero.feature_auth.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.BrandBlue
import com.example.habithero.core.designsystem.BrandGreen
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.feature_auth.presentation.components.AuthTextField
import com.example.habithero.feature_auth.presentation.components.ResetPasswordDialog
import com.example.habithero.feature_auth.presentation.components.SocialLoginRow

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showResetDialog by remember { mutableStateOf(false) }
    var resetEmail by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo_full),
            contentDescription = "Logo",
            modifier = Modifier.size(250.dp)
        )

        AuthTextField(valueState = mutableStateOf(email), label = "Email")
        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(valueState = mutableStateOf(password), label = "Password", isPassword = true)

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "¿Olvidaste la contraseña?",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = BrandBlue),
            modifier = Modifier.clickable { showResetDialog = true }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLoginSuccess,
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
        ) {
            Text("Log In", color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onSignUpClick,
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
        ) {
            Text("Sign Up", color = MaterialTheme.colorScheme.onPrimary)
        }


        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "O continúa con", style = MaterialTheme.typography.labelSmall)

        Spacer(modifier = Modifier.height(8.dp))
        SocialLoginRow(
            onGoogleClick = { /* TODO */ },
            onAppleClick = { /* TODO */ },
            onFacebookClick = { /* TODO */ }
        )
    }


    // Modal de reset de contraseña
    if (showResetDialog) {
        ResetPasswordDialog(
            resetEmail = resetEmail,
            onEmailChange = { resetEmail = it },
            onDismiss = { showResetDialog = false },
            onConfirm = {
            // TODO: lógica para resetear contraseña con resetEmail
                showResetDialog = false
            } )
        }
    }



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HabitHeroTheme {
        LoginScreen(onLoginSuccess = {}, onSignUpClick = {})
    }
}
