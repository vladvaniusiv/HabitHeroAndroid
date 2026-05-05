package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.User
import com.example.habithero.data.local.datasource.UserLocalDataSource
import com.example.habithero.data.remote.datasource.AuthRemoteDataSource
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AuthRepositoryImplTest {

    private val remote = mockk<AuthRemoteDataSource>(relaxed = true)
    private val local = mockk<UserLocalDataSource>(relaxed = true)

    private val repo = AuthRepositoryImpl(remote, local)

    @Test
    fun login_calls_remote_and_saves_user_locally() = runTest {
        repo.login("test@mail.com", "1234")

        coVerify { remote.login("test@mail.com", "1234") }
        coVerify { local.saveUser(any()) }
    }

    @Test
    fun register_calls_remote_and_saves_user_locally() = runTest {
        val user = User(null, "Vlad", "vlad", "vlad@mail.com", "1234")

        repo.register(user)

        coVerify { remote.register(any()) }
        coVerify { local.saveUser(any()) }
    }
}
