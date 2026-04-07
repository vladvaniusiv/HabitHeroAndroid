package com.example.habithero.feature_settings.settings

import app.cash.turbine.test
import com.example.habithero.core.domain.repository.SettingsRepository
import com.example.habithero.feature_settings.presentation.settings.*
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: SettingsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        settingsRepository = mockk(relaxed = true)

        viewModel = SettingsViewModel(settingsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun estadoInicial_carga_datos_fake() = runTest {
        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals("Vlad Vaniusiv", state.name)
        assertEquals("@vladvan", state.userName)
        assertEquals("vlad@gmail.com", state.email)
        assertFalse(state.isLoading)
    }

    @Test
    fun onNameChanged_actualiza_el_estado() = runTest {
        viewModel.onAction(SettingsAction.OnNameChanged("Nuevo Nombre"))

        assertEquals("Nuevo Nombre", viewModel.uiState.value.name)
    }

    @Test
    fun onUserNameChanged_actualiza_el_estado() = runTest {
        viewModel.onAction(SettingsAction.OnUserNameChanged("@nuevo"))

        assertEquals("@nuevo", viewModel.uiState.value.userName)
    }

    @Test
    fun onEmailChanged_actualiza_el_estado() = runTest {
        viewModel.onAction(SettingsAction.OnEmailChanged("nuevo@gmail.com"))

        assertEquals("nuevo@gmail.com", viewModel.uiState.value.email)
    }

    @Test
    fun onPasswordChange_contrasenas_no_coinciden_emite_mensaje() = runTest {
        viewModel.events.test {
            viewModel.onAction(SettingsAction.OnPasswordChange("old", "1234", "9999"))

            advanceUntilIdle()

            val event = awaitItem()
            assertTrue(event is SettingsEvent.ShowMessage)
            assertEquals("Las contraseñas no coinciden", (event as SettingsEvent.ShowMessage).message)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onPasswordChange_contrasenas_coinciden_emite_mensaje_exito() = runTest {
        viewModel.events.test {
            viewModel.onAction(SettingsAction.OnPasswordChange("old", "1234", "1234"))

            advanceUntilIdle()

            val event = awaitItem()
            assertTrue(event is SettingsEvent.ShowMessage)
            assertEquals("Contraseña actualizada", (event as SettingsEvent.ShowMessage).message)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onAvatarClicked_emite_selector_avatar() = runTest {
        viewModel.events.test {
            viewModel.onAction(SettingsAction.OnAvatarClicked)

            advanceUntilIdle()

            assertEquals(SettingsEvent.ShowAvatarPicker, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onBackClicked_emite_navegacion_atras() = runTest {
        viewModel.events.test {
            viewModel.onAction(SettingsAction.OnBackClicked)

            advanceUntilIdle()

            assertEquals(SettingsEvent.NavigateBack, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onNavigateHome_emite_navegacion_home() = runTest {
        viewModel.events.test {
            viewModel.onAction(SettingsAction.OnNavigateHome)

            advanceUntilIdle()

            assertEquals(SettingsEvent.NavigateToHome, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onNavigateStats_emite_navegacion_stats() = runTest {
        viewModel.events.test {
            viewModel.onAction(SettingsAction.OnNavigateStats)

            advanceUntilIdle()

            assertEquals(SettingsEvent.NavigateToStats, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
