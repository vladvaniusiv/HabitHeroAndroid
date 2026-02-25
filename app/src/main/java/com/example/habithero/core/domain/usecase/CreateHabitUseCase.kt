package com.example.habithero.core.domain.usecase

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.repository.HabitRepository

class CreateHabitUseCase(
    private val repository: HabitRepository
) {

    suspend operator fun invoke(habit: Habit) {
        if (habit.title.isBlank()) {
            throw IllegalArgumentException("El título no puede estar vacío")
        }
        if (habit.description.isBlank()) {
            throw IllegalArgumentException("La descripción no puede estar vacía")
        }

        repository.createHabit(habit)
    }
}
