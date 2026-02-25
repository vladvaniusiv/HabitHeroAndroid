package com.example.habithero.usecase

import com.example.habithero.core.domain.repository.AuthRepository
import com.example.habithero.core.domain.usecase.LoginUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class LoginUseCaseTest {

    private val repository = mockk<AuthRepository>(relaxed = true)
    private val useCase = LoginUseCase(repository)

    @Test
    fun login_falla_con_email_vacio() = runTest {
        assertFailsWith<IllegalArgumentException> {
            useCase("", "1234")
        }
    }

    @Test
    fun login_falla_con_pass_vacio() = runTest {
        assertFailsWith<IllegalArgumentException> {
            useCase("test@example.com", "")
        }
    }

    @Test
    fun login_llama_al_repo_si_todo_ok() = runTest {
        useCase("test@example.com", "1234")

        coVerify { repository.login("test@example.com", "1234") }
    }
}