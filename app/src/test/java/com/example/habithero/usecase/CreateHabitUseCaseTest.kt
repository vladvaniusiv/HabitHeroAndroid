package com.example.habithero.usecase

import com.example.habithero.core.domain.model.Habit
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.core.domain.usecase.CreateHabitUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class CreateHabitUseCaseTest {

    private val repository = mockk<HabitRepository>(relaxed = true)
    private val useCase = CreateHabitUseCase(repository)

    private val validHabit = Habit(
        id = null,
        userId = 1,
        title = "Leer",
        description = "Leer 20 minutos"
    )

    @Test
    fun create_habit_falla_con_titulo_vacio() = runTest {
        val habit = validHabit.copy(title = "")
        assertFailsWith<IllegalArgumentException> { useCase(habit) }
    }

    @Test
    fun create_habit_falla_con_descr_vacia() = runTest {
        val habit = validHabit.copy(description = "")
        assertFailsWith<IllegalArgumentException> { useCase(habit) }
    }

    @Test
    fun create_habit_llama_al_repo_si_todo_ok() = runTest {
        useCase(validHabit)

        coVerify { repository.createHabit(validHabit) }
    }
}
