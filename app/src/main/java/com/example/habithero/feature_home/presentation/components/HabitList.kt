package com.example.habithero.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.R
import com.example.habithero.core.designsystem.BrandGreen
import com.example.habithero.core.designsystem.HabitHeroShapes
import com.example.habithero.core.domain.model.Habit

@Composable
fun HabitList(
    habits: List<Habit>,
    onHabitChecked: (Int, Boolean) -> Unit,
    onCreateHabit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandGreen.copy(alpha = 0.1f), shape = HabitHeroShapes.large)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.habit_list),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        habits.forEach { habit ->
            HabitItem(
                habitName = habit.title,
                isCompleted = habit.active, // Aquí debes mapear si está completado hoy usando tu lógica local
                onCheckedChange = { isChecked ->
                    // Como el ID es opcional (Int?), usamos el operador Elvis o un fallback por seguridad (?: 0)
                    onHabitChecked(habit.id ?: 0, isChecked)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = onCreateHabit,
            modifier = Modifier.fillMaxWidth(),
            shape = HabitHeroShapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = BrandGreen)
        ) {
            Text(stringResource(R.string.create_habit), color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitListPreview() {
    MaterialTheme {
        HabitList(
            habits = listOf(
                Habit(id = 1, title = "Hacer ejercicio", description = "", userId = 1, active = false),
                Habit(id = 2, title = "Leer 20 páginas", description = "", userId = 1, active = true)
            ),
            onHabitChecked = { _, _ -> },
            onCreateHabit = {}
        )
    }
}