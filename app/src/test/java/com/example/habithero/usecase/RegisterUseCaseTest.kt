package com.example.habithero.usecase

import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.AuthRepository
import com.example.habithero.core.domain.usecase.RegisterUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class RegisterUseCaseTest {

    private val repository = mockk<AuthRepository>(relaxed = true)
    private val useCase = RegisterUseCase(repository)

    private val validUser = User(
        id = null,
        name = "Vlad",
        username = "vlad123",
        email = "vlad@example.com",
        password = "1234"
    )

    @Test
    fun registro_falla_con_name_vacio() = runTest {
        val user = validUser.copy(name = "")
        assertFailsWith<IllegalArgumentException> { useCase(user) }
    }

    @Test
    fun registro_falla_con_username_vacio() = runTest {
        val user = validUser.copy(username = "")
        assertFailsWith<IllegalArgumentException> { useCase(user) }
    }

    @Test
    fun registro_falla_con_email_vacio() = runTest {
        val user = validUser.copy(email = "")
        assertFailsWith<IllegalArgumentException> { useCase(user) }
    }

    @Test
    fun registro_falla_con_pass_corta() = runTest {
        val user = validUser.copy(password = "12")
        assertFailsWith<IllegalArgumentException> { useCase(user) }
    }

    @Test
    fun registro_llama_al_repo_si_todo_ok() = runTest {
        useCase(validUser)

        coVerify { repository.register(validUser) }
    }
}
