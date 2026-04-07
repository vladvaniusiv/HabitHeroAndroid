package com.example.habithero.feature_auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.HabitHeroTheme

@Composable
fun SocialLoginRow(
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    onFacebookClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.google),
            contentDescription = stringResource(R.string.google),
            modifier = Modifier
                .size(40.dp)
                .clickable { onGoogleClick() }
        )
        Image(
            painter = painterResource(R.drawable.apple),
            contentDescription = stringResource(R.string.apple),
            modifier = Modifier
                .size(40.dp)
                .clickable { onAppleClick() }
        )
        Image(
            painter = painterResource(R.drawable.facebook),
            contentDescription = stringResource(R.string.facebook),
            modifier = Modifier
                .size(40.dp)
                .clickable { onFacebookClick() }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SocialLoginRowPreview() {
    HabitHeroTheme {
        SocialLoginRow(
            onGoogleClick = {},
            onAppleClick = {},
            onFacebookClick = {}
        )
    }
}
