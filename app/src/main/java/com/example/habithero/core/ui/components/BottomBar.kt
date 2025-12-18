package com.example.habithero.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R

sealed class BottomNavItem(val route: String, val iconRes: Int, val labelRes: Int) {
    object Home : BottomNavItem("home", R.drawable.home, R.string.home)
    object Stats : BottomNavItem("stats", R.drawable.statistics, R.string.stats)
    object Settings : BottomNavItem("settings", R.drawable.config, R.string.settings)
}

@Composable
fun BottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp
    ) {
        listOf(BottomNavItem.Home, BottomNavItem.Stats, BottomNavItem.Settings).forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = stringResource(item.labelRes),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = stringResource(item.labelRes)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    MaterialTheme {
        BottomBar(
            currentRoute = "home",
            onNavigate = {}
        )
    }
}