package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.HabitProgressDao
import com.example.habithero.data.local.entity.HabitProgressEntity
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class StatsLocalDataSourceTest {

    private val dao = mockk<HabitProgressDao>(relaxed = true)
    private val dataSource = StatsLocalDataSource(dao)

    @Test
    fun getWeeklyStats_delegates_to_DAO() = runTest {
        every { dao.getProgress(any(), any(), any()) } returns flowOf(emptyList())

        dataSource.getWeeklyStats(1, "2024-01-01", "2024-01-07")

        coVerify { dao.getProgress(1, "2024-01-01", "2024-01-07") }
    }

    @Test
    fun saveWeeklyStats_inserts_into_DAO() = runTest {
        val list = listOf(HabitProgressEntity(1, 1, "2024-01-01", true))

        dataSource.saveWeeklyStats(list)

        coVerify { dao.insertProgress(list) }
    }
}
