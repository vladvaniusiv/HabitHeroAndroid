package com.example.habithero.data.repository

import app.cash.turbine.test
import com.example.habithero.core.domain.model.Habit
import com.example.habithero.data.local.datasource.HabitLocalDataSource
import com.example.habithero.data.local.entity.HabitEntity
import com.example.habithero.data.remote.datasource.HabitRemoteDataSource
import com.example.habithero.data.remote.dto.HabitDto
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HabitRepositoryImplTest {

    private val local = mockk<HabitLocalDataSource>(relaxed = true)
    private val remote = mockk<HabitRemoteDataSource>(relaxed = true)

    private val repo = HabitRepositoryImpl(local, remote)

    @Test
    fun Offline_First_emits_local_then_remote() = runTest {

        every { local.getHabits(1) } returns flowOf(
            listOf(HabitEntity(1, 1, "Local", "Desc", true))
        )

        coEvery { remote.getHabits(1) } returns listOf(
            HabitDto(2, 1, "Remote", "Desc", true)
        )

        repo.getHabitsForUser(1).test {

            val first = awaitItem()
            assert(first[0].id == 1)

            val second = awaitItem()
            assert(second[0].id == 2)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
