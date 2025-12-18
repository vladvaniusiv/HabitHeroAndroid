package com.example.habithero.feature_home.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.core.ui.components.BottomBar
import com.example.habithero.core.ui.components.TopBar
import com.example.habithero.feature_home.presentation.components.HabitList
import com.example.habithero.feature_home.presentation.components.ProgressSummary

// Método para modificar avatar
private fun changeAvatar() {
    // TODO: implementar lógica para cambiar la foto del avatar
}

@Composable
fun HomeScreen(
    onNavigateToStats: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    var habits by remember {
        mutableStateOf(
            listOf(
                "Meditar 10 minutos" to true,
                "Leer 20 páginas" to true,
                "Beber 2L de agua" to true,
                "Caminar 30 minutos" to false
            )
        )
    }

    val progress = habits.count { it.second }.toFloat() / habits.size

    Scaffold(
        topBar = {
            TopBar(titleRes = R.string.home)
        },
        bottomBar = {
            BottomBar(
                currentRoute = "home",
                onNavigate = { route ->
                    when (route) {
                        "stats" -> onNavigateToStats()
                        "settings" -> onNavigateToSettings()
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar centrado con opción de modificar
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clickable {
                        changeAvatar()
                    }
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Vlad Vaniusiv", style = MaterialTheme.typography.titleLarge)
            Text("@vladvan", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de hábitos
            HabitList(
                habits = habits,
                onHabitChecked = { name, checked ->
                    habits = habits.map {
                        if (it.first == name) name to checked else it
                    }
                },
                onCreateHabit = {
                    // TODO: lógica para crear hábito
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Resumen de progreso
            ProgressSummary(progress = progress)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HabitHeroTheme {
        HomeScreen(onNavigateToStats = {}, onNavigateToSettings = {})
    }
}
