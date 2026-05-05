package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.HabitDao
import com.example.habithero.data.local.entity.HabitEntity
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HabitLocalDataSourceTest {

    private val dao = mockk<HabitDao>(relaxed = true)
    private val dataSource = HabitLocalDataSource(dao)

    @Test
    fun getHabits_delegates_to_DAO() = runTest {
        every { dao.getHabits(1) } returns flowOf(emptyList())

        dataSource.getHabits(1)

        coVerify { dao.getHabits(1) }
    }

    @Test
    fun saveHabits_inserts_into_DAO() = runTest {
        val list = listOf(HabitEntity(1, 1, "Title", "Desc", true))

        dataSource.saveHabits(list)

        coVerify { dao.insertHabits(list) }
    }
}
