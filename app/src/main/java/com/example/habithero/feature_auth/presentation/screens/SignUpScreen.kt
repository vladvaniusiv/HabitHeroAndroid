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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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


@SuppressLint("UnrememberedMutableState")
@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onBackToLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

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

        Text("Sign Up", style = MaterialTheme.typography.titleLarge)
        Text("Crea una cuenta para empezar", style = MaterialTheme.typography.labelSmall)

        Spacer(modifier = Modifier.height(16.dp))
        AuthTextField(valueState = mutableStateOf(name), label = "Nombre")
        Spacer(modifier = Modifier.height(8.dp))
        AuthTextField(valueState = mutableStateOf(email), label = "Email")
        Spacer(modifier = Modifier.height(8.dp))
        AuthTextField(valueState = mutableStateOf(password), label = "Crea la contraseña", isPassword = true)
        Spacer(modifier = Modifier.height(8.dp))
        AuthTextField(valueState = mutableStateOf(confirmPassword), label = "Confirma la contraseña", isPassword = true)

        Spacer(modifier = Modifier.height(8.dp))
        var showTermsDialog by remember { mutableStateOf(false) }
        var showPrivacyDialog by remember { mutableStateOf(false) }


        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = acceptedTerms, onCheckedChange = { acceptedTerms = it })

            val annotatedText = buildAnnotatedString {
                append("He leído y acepto ")

                pushStringAnnotation(tag = "TERMS", annotation = "terms")
                withStyle(style = SpanStyle(color = BrandBlue)) {
                    append("Términos y Condiciones")
                }
                pop()

                append(" y ")

                pushStringAnnotation(tag = "PRIVACY", annotation = "privacy")
                withStyle(style = SpanStyle(color = BrandBlue)) {
                    append("Política de Privacidad")
                }
                pop()
            }

            ClickableText(
                text = annotatedText,
                style = MaterialTheme.typography.labelSmall,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(tag = "TERMS", start = offset, end = offset)
                        .firstOrNull()?.let { showTermsDialog = true }
                    annotatedText.getStringAnnotations(tag = "PRIVACY", start = offset, end = offset)
                        .firstOrNull()?.let { showPrivacyDialog = true }
                }
            )
        }


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



        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSignUpSuccess,
            modifier = Modifier.fillMaxWidth(0.8f),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
        ) {
            Text("Sign Up", color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Volver a login",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = BrandBlue),
            modifier = Modifier.clickable { onBackToLogin() }
        )

        Spacer(modifier = Modifier.height(16.dp))
        SocialLoginRow(
            onGoogleClick = { /* TODO */ },
            onAppleClick = { /* TODO */ },
            onFacebookClick = { /* TODO */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    HabitHeroTheme {
        SignUpScreen(onSignUpSuccess = {}, onBackToLogin = {})
    }
}
