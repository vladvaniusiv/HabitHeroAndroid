package com.example.habithero.feature_auth.signup

import app.cash.turbine.test
import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.usecase.RegisterUseCase
import com.example.habithero.feature_auth.presentation.signup.*
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var registerUseCase: RegisterUseCase
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        registerUseCase = mockk()
        viewModel = RegisterViewModel(registerUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onEmailChanged_actualiza_el_estado() = runTest {
        viewModel.onAction(RegisterAction.OnEmailChanged("test@gmail.com"))
        assertEquals("test@gmail.com", viewModel.uiState.value.email)
    }

    @Test
    fun onPasswordChanged_actualiza_el_estado() = runTest {
        viewModel.onAction(RegisterAction.OnPasswordChanged("1234"))
        assertEquals("1234", viewModel.uiState.value.password)
    }

    @Test
    fun onSignUpClicked_llama_al_caso_de_uso_y_navega_a_Home() = runTest {
        coEvery { registerUseCase(any()) } returns Unit

        viewModel.onAction(RegisterAction.OnNameChanged("Vlad"))
        viewModel.onAction(RegisterAction.OnEmailChanged("test@gmail.com"))
        viewModel.onAction(RegisterAction.OnPasswordChanged("1234"))
        viewModel.onAction(RegisterAction.OnConfirmPasswordChanged("1234"))
        viewModel.onAction(RegisterAction.OnTermsAccepted(true))

        viewModel.events.test {
            viewModel.onAction(RegisterAction.OnSignUpClicked)

            advanceUntilIdle()

            assertEquals(RegisterEvent.NavigateToHome, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        coVerify {
            registerUseCase(
                User(
                    id = null,
                    name = "Vlad",
                    username = "vlad",
                    email = "test@gmail.com",
                    password = "1234"
                )
            )
        }
    }

    @Test
    fun onSignUpClicked_emite_error_cuando_el_caso_de_uso_falla() = runTest {
        coEvery { registerUseCase(any()) } throws IllegalArgumentException("Email en uso")

        viewModel.onAction(RegisterAction.OnNameChanged("Vlad"))
        viewModel.onAction(RegisterAction.OnEmailChanged("bad@gmail.com"))
        viewModel.onAction(RegisterAction.OnPasswordChanged("wrong"))
        viewModel.onAction(RegisterAction.OnConfirmPasswordChanged("wrong"))
        viewModel.onAction(RegisterAction.OnTermsAccepted(true))

        viewModel.events.test {
            viewModel.onAction(RegisterAction.OnSignUpClicked)

            advanceUntilIdle()

            val event = awaitItem()
            assertTrue(event is RegisterEvent.ShowError)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
