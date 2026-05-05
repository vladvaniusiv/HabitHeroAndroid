package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.UserDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AuthRemoteDataSourceTest {

    private val api = mockk<HabitHeroApi>(relaxed = true)
    private val dataSource = AuthRemoteDataSource(api)

    @Test
    fun login_calls_API_with_correct_parameters() = runTest {
        coEvery { api.login(any()) } returns Unit

        dataSource.login("test@mail.com", "1234")

        coVerify {
            api.login(
                UserDto(
                    id = null,
                    name = "",
                    email = "test@mail.com",
                    password = "1234"
                )
            )
        }
    }

    @Test
    fun register_calls_API_with_correct_DTO() = runTest {
        val dto = UserDto(null, "Vlad", "vlad@mail.com", "1234")

        dataSource.register(dto)

        coVerify { api.register(dto) }
    }
}
