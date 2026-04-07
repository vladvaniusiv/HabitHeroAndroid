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

@Composable
fun HabitList(
    habits: List<Pair<String, Boolean>>,
    onHabitChecked: (String, Boolean) -> Unit,
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

        habits.forEach { (name, completed) ->
            HabitItem(
                habitName = name,
                isCompleted = completed,
                onCheckedChange = { onHabitChecked(name, it) }
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
                "Hacer ejercicio" to false,
                "Leer 20 páginas" to true
            ),
            onHabitChecked = { _, _ -> },
            onCreateHabit = {}
        )
    }
}