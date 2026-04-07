package com.example.habithero.feature_auth.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.BrandBlue
import com.example.habithero.core.designsystem.BrandGreen
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.core.resources.PrivacyPolicyText
import com.example.habithero.core.resources.TermsAndConditionsText
import com.example.habithero.feature_auth.presentation.components.AuthTextField
import com.example.habithero.feature_auth.presentation.components.SocialLoginRow
import com.example.habithero.feature_auth.presentation.signup.RegisterAction
import com.example.habithero.feature_auth.presentation.signup.RegisterUiState


@SuppressLint("UnrememberedMutableState")
@Composable
fun SignUpScreen(
    uiState: RegisterUiState,
    onAction: (RegisterAction) -> Unit
) {
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

        Text(stringResource(R.string.sign_up), style = MaterialTheme.typography.titleLarge)
        Text(stringResource(R.string.sign_up_description), style = MaterialTheme.typography.labelSmall)

        Spacer(modifier = Modifier.height(16.dp))
        // NAME
        val nameState = remember(uiState.name) { mutableStateOf(uiState.name) }
        AuthTextField(valueState = nameState, label = stringResource(R.string.name))
        LaunchedEffect(nameState.value) {
            if (nameState.value != uiState.name)
                onAction(RegisterAction.OnNameChanged(nameState.value))
        }
        Spacer(modifier = Modifier.height(8.dp))
        // EMAIL
        val emailState = remember(uiState.email) { mutableStateOf(uiState.email) }
        AuthTextField(valueState = emailState, label = stringResource(R.string.email))
        LaunchedEffect(emailState.value) {
            if (emailState.value != uiState.email)
                onAction(RegisterAction.OnEmailChanged(emailState.value))
        }
        Spacer(modifier = Modifier.height(8.dp))
        // PASSWORD
        val passState = remember(uiState.password) { mutableStateOf(uiState.password) }
        AuthTextField(valueState = passState, label = stringResource(R.string.create_password_label), isPassword = true)
        LaunchedEffect(passState.value) {
            if (passState.value != uiState.password)
                onAction(RegisterAction.OnPasswordChanged(passState.value))
        }
        Spacer(modifier = Modifier.height(8.dp))
        // CONFIRM PASSWORD
        val confirmState = remember(uiState.confirmPassword) { mutableStateOf(uiState.confirmPassword) }
        AuthTextField(valueState = confirmState, label = stringResource(R.string.confirm_password_label), isPassword = true)
        LaunchedEffect(confirmState.value) {
            if (confirmState.value != uiState.confirmPassword)
                onAction(RegisterAction.OnConfirmPasswordChanged(confirmState.value))
        }

        Spacer(modifier = Modifier.height(8.dp))

        var showTermsDialog by remember { mutableStateOf(false) }
        var showPrivacyDialog by remember { mutableStateOf(false) }

        // TERMS CHECKBOX
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = uiState.acceptedTerms,
                onCheckedChange = { onAction(RegisterAction.OnTermsAccepted(it)) }
            )

            val annotatedText = buildAnnotatedString {
                append(stringResource(R.string.read_and_accept))

                pushStringAnnotation(tag = "TERMS", annotation = "terms")
                withStyle(style = SpanStyle(color = BrandBlue)) {
                    append(stringResource(R.string.terms_conditions_link))
                }
                pop()

                append(stringResource(R.string.and))

                pushStringAnnotation(tag = "PRIVACY", annotation = "privacy")
                withStyle(style = SpanStyle(color = BrandBlue)) {
                    append(stringResource(R.string.privacy_policy_link))
                }
                pop()
            }

            @Suppress("DEPRECATION")
            ClickableText(
                text = annotatedText,
                style = MaterialTheme.typography.labelSmall,
                onClick = { offset ->
                    annotatedText.getStringAnnotations("TERMS", offset, offset)
                        .firstOrNull()?.let { showTermsDialog = true }

                    annotatedText.getStringAnnotations("PRIVACY", offset, offset)
                        .firstOrNull()?.let { showPrivacyDialog = true }
                }
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onAction(RegisterAction.OnSignUpClicked) },
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
        ) {
            Text(stringResource(R.string.sign_up), color = MaterialTheme.colorScheme.onPrimary)
        }

        uiState.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.return_to_login),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = BrandBlue
            ),
            modifier = Modifier.clickable {
                onAction(RegisterAction.OnBackToLoginClicked)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        SocialLoginRow(
            onGoogleClick = {},
            onAppleClick = {},
            onFacebookClick = {}
        )

        if (showTermsDialog) {
            AlertDialog(
                onDismissRequest = { showTermsDialog = false },
                confirmButton = {
                    TextButton(onClick = { showTermsDialog = false }) { Text("Cerrar") }
                },
                title = { Text("Términos y Condiciones") },
                text = { TermsAndConditionsText() }
            )
        }

        if (showPrivacyDialog) {
            AlertDialog(
                onDismissRequest = { showPrivacyDialog = false },
                confirmButton = {
                    TextButton(onClick = { showPrivacyDialog = false }) { Text("Cerrar") }
                },
                title = { Text("Política de Privacidad") },
                text = { PrivacyPolicyText() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    HabitHeroTheme {
        SignUpScreen(
            uiState = RegisterUiState(),
            onAction = {}
        )
    }
}
