package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.HabitDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HabitRemoteDataSourceTest {

    private val api = mockk<HabitHeroApi>(relaxed = true)
    private val dataSource = HabitRemoteDataSource(api)

    @Test
    fun getHabits_calls_API() = runTest {
        coEvery { api.getHabits(any()) } returns emptyList()

        dataSource.getHabits(1)

        coVerify { api.getHabits(1) }
    }

    @Test
    fun createHabit_calls_API() = runTest {
        val dto = HabitDto(1, 1, "Title", "Desc", true)

        dataSource.createHabit(dto)

        coVerify { api.createHabit(dto) }
    }
}
