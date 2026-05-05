package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.UserDao
import com.example.habithero.data.local.entity.UserEntity
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserLocalDataSourceTest {

    private val dao = mockk<UserDao>(relaxed = true)
    private val dataSource = UserLocalDataSource(dao)

    @Test
    fun getUser_delegates_to_DAO() = runTest {
        every { dao.getUser() } returns flowOf(null)

        dataSource.getUser()

        coVerify { dao.getUser() }
    }

    @Test
    fun saveUser_inserts_into_DAO() = runTest {
        val user = UserEntity(1, "Vlad", "vlad@mail.com")

        dataSource.saveUser(user)

        coVerify { dao.saveUser(user) }
    }
}
