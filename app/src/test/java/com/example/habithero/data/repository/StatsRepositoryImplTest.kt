package com.example.habithero.data.repository

import app.cash.turbine.test
import com.example.habithero.core.domain.model.HabitProgress
import com.example.habithero.data.local.datasource.StatsLocalDataSource
import com.example.habithero.data.local.entity.HabitProgressEntity
import com.example.habithero.data.remote.datasource.StatsRemoteDataSource
import com.example.habithero.data.remote.dto.HabitProgressDto
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class StatsRepositoryImplTest {

    private val local = mockk<StatsLocalDataSource>(relaxed = true)
    private val remote = mockk<StatsRemoteDataSource>(relaxed = true)

    private val repo = StatsRepositoryImpl(local, remote)

    @Test
    fun Offline_First_emits_local_first_then_remote() = runTest {

        every { local.getWeeklyStats(1, "A", "B") } returns flowOf(
            listOf(HabitProgressEntity(1, 1, "2024-01-01", true))
        )

        coEvery { remote.getWeeklyStats(1, "A", "B") } returns listOf(
            HabitProgressDto(2, 1, "2024-01-02", false)
        )

        repo.getWeeklyStats(1, "A", "B").test {

            // 1. Local emit
            val first = awaitItem()
            assert(first.size == 1)
            assert(first[0].id == 1)

            // 2. Remote emit (after saving)
            val second = awaitItem()
            assert(second.size == 1)
            assert(second[0].id == 2)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
