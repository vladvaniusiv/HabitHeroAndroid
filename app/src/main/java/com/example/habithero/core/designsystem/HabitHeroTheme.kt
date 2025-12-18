package com.example.habithero.core.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val HabitHeroColorScheme = lightColorScheme(
    primary = BrandGreen,
    secondary = BrandBlue,
    background = BackgroundPrimary,
    surface = SurfacePrimary,
    onPrimary = Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = Error
)

@Composable
fun HabitHeroTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = HabitHeroColorScheme,
        typography = HabitHeroTypography,
        shapes = HabitHeroShapes,
        content = content
    )
}
