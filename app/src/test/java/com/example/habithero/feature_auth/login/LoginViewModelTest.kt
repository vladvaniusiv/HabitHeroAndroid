package com.example.habithero.feature_auth.login

import app.cash.turbine.test
import com.example.habithero.core.domain.usecase.LoginUseCase
import com.example.habithero.feature_auth.presentation.login.*
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        loginUseCase = mockk()
        viewModel = LoginViewModel(loginUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onEmailChanged_actualiza_el_estado() = runTest {
        viewModel.onAction(LoginAction.OnEmailChanged("test@gmail.com"))
        assertEquals("test@gmail.com", viewModel.uiState.value.email)
    }

    @Test
    fun onPasswordChanged_actualiza_el_estado() = runTest {
        viewModel.onAction(LoginAction.OnPasswordChanged("1234"))
        assertEquals("1234", viewModel.uiState.value.password)
    }

    @Test
    fun onLoginClicked_llama_al_caso_de_uso_y_navega_al_home() = runTest {
        coEvery { loginUseCase("test@gmail.com", "1234") } returns Unit

        viewModel.onAction(LoginAction.OnEmailChanged("test@gmail.com"))
        viewModel.onAction(LoginAction.OnPasswordChanged("1234"))

        viewModel.events.test {
            viewModel.onAction(LoginAction.OnLoginClicked)

            advanceUntilIdle()

            assertEquals(LoginEvent.NavigateToHome, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { loginUseCase("test@gmail.com", "1234") }
    }

    @Test
    fun onLoginClicked_emite_error_cuando_el_caso_de_uso_falla() = runTest {
        coEvery { loginUseCase(any(), any()) } throws IllegalArgumentException("Credenciales inválidas")

        viewModel.onAction(LoginAction.OnEmailChanged("bad@gmail.com"))
        viewModel.onAction(LoginAction.OnPasswordChanged("wrong"))

        viewModel.events.test {
            viewModel.onAction(LoginAction.OnLoginClicked)

            advanceUntilIdle()

            val event = awaitItem()
            assertTrue(event is LoginEvent.ShowError)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
