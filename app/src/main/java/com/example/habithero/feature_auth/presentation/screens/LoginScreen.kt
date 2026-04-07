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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.BrandBlue
import com.example.habithero.core.designsystem.BrandGreen
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.feature_auth.presentation.login.LoginAction
import com.example.habithero.feature_auth.presentation.login.LoginUiState
import com.example.habithero.feature_auth.presentation.components.AuthTextField
import com.example.habithero.feature_auth.presentation.components.ResetPasswordDialog
import com.example.habithero.feature_auth.presentation.components.SocialLoginRow

@SuppressLint("UnrememberedMutableState")
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onAction: (LoginAction) -> Unit,
    onSignUpClick: () -> Unit
) {
    var showResetDialog = remember { mutableStateOf(false) }
    var resetEmail = remember { mutableStateOf(uiState.email) }

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

        val emailState = remember(uiState.email) { mutableStateOf(uiState.email) }

        AuthTextField(
            valueState = emailState,
            label = stringResource(R.string.email)
        )

        LaunchedEffect(emailState.value) {
            if (emailState.value != uiState.email) {
                onAction(LoginAction.OnEmailChanged(emailState.value))
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        val passwordState = remember(uiState.password) { mutableStateOf(uiState.password) }

        AuthTextField(
            valueState = passwordState,
            label = stringResource(R.string.password),
            isPassword = true
        )

        LaunchedEffect(passwordState.value) {
            if (passwordState.value != uiState.password) {
                onAction(LoginAction.OnPasswordChanged(passwordState.value))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = BrandBlue),
            modifier = Modifier.clickable { showResetDialog.value = true }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onAction(LoginAction.OnLoginClicked) },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
        ) {
            Text(stringResource(R.string.log_in), color = MaterialTheme.colorScheme.onPrimary)
        }

        uiState.errorMessage?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onSignUpClick,
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
        ) {
            Text(stringResource(R.string.sign_up), color = MaterialTheme.colorScheme.onPrimary)
        }


        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(R.string.continue_with), style = MaterialTheme.typography.labelSmall)

        Spacer(modifier = Modifier.height(8.dp))
        SocialLoginRow(
            onGoogleClick = { /* TODO */ },
            onAppleClick = { /* TODO */ },
            onFacebookClick = { /* TODO */ }
        )
    }

    if (showResetDialog.value) {
        ResetPasswordDialog(
            resetEmail = resetEmail.value,
            onEmailChange = { resetEmail.value = it },
            onDismiss = { showResetDialog.value = false },
            onConfirm = {
                // TODO llamar a un caso de uso
                showResetDialog.value = false
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HabitHeroTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onAction = {},
            onSignUpClick = {}
        )
    }
}
