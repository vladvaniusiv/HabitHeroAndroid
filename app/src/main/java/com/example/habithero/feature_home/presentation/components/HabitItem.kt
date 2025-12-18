package com.example.habithero.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habithero.core.designsystem.BrandPurple
import com.example.habithero.core.designsystem.HabitHeroShapes

@Composable
fun HabitItem(
    habitName: String,
    isCompleted: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = HabitHeroShapes.large)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = habitName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = isCompleted,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(24.dp),
            colors = androidx.compose.material3.CheckboxDefaults.colors(
                checkedColor = BrandPurple,
                uncheckedColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HabitItemPreview() {
    MaterialTheme {
        HabitItem(
            habitName = "Leer 2 libros",
            isCompleted = false,
            onCheckedChange = {}
        )
    }
}