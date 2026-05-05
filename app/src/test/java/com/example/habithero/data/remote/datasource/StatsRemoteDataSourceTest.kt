package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class StatsRemoteDataSourceTest {

    private val api = mockk<HabitHeroApi>(relaxed = true)
    private val dataSource = StatsRemoteDataSource(api)

    @Test
    fun getWeeklyStats_calls_API() = runTest {
        coEvery { api.getWeeklyStats(any()) } returns emptyList()

        dataSource.getWeeklyStats(1, "2024-01-01", "2024-01-07")

        coVerify { api.getWeeklyStats(any()) }
    }
}
