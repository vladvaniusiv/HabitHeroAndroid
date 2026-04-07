package com.example.habithero.core.resources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.ui.components.BottomBar

@Composable
fun TermsAndConditionsText() {
    Column {
        Text(stringResource(R.string.terms_conditions_title), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.habithero), style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.general_info_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.general_info_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.users_register_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.users_register_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.permitted_use_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.permitted_use_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.features_availability_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.features_availability_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.admin_panel_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.admin_panel_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.intellectual_property_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.intellectual_property_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.responsibility_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.responsibility_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.modifications_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.modifications_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.applicable_law_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.applicable_law_text),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun PrivacyPolicyText() {
    Column {
        Text(stringResource(R.string.privacy_policy_title), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.habithero), style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_intro_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_intro_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_data_collected_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_data_collected_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_purpose_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_purpose_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_data_retention_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_data_retention_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_data_sharing_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_data_sharing_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_security_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_security_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_user_rights_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_user_rights_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_policy_changes_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_policy_changes_text),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.privacy_acceptance_title), style = MaterialTheme.typography.titleMedium)
        Text(
            stringResource(R.string.privacy_acceptance_text),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TermsAndConditionsTextPreview() {
    MaterialTheme {
        TermsAndConditionsText()
        //PrivacyPolicyText()
    }
}