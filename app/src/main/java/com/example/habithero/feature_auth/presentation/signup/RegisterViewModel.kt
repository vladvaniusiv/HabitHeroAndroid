package com.example.habithero.feature_auth.presentation.signup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.usecase.RegisterUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _events = Channel<RegisterEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {

            is RegisterAction.OnNameChanged ->
                _uiState.update { it.copy(name = action.value, errorMessage = null) }

            is RegisterAction.OnEmailChanged ->
                _uiState.update { it.copy(email = action.value, errorMessage = null) }

            is RegisterAction.OnPasswordChanged ->
                _uiState.update { it.copy(password = action.value, errorMessage = null) }

            is RegisterAction.OnConfirmPasswordChanged ->
                _uiState.update { it.copy(confirmPassword = action.value, errorMessage = null) }

            is RegisterAction.OnTermsAccepted ->
                _uiState.update { it.copy(acceptedTerms = action.value) }

            RegisterAction.OnSignUpClicked -> register()

            RegisterAction.OnBackToLoginClicked ->
                viewModelScope.launch { _events.send(RegisterEvent.NavigateBackToLogin) }
        }
    }

    private fun register() {
        val state = _uiState.value

        if (!state.acceptedTerms) {
            viewModelScope.launch {
                _events.send(RegisterEvent.ShowError("Debes aceptar los términos"))
            }
            return
        }

        if (state.password != state.confirmPassword) {
            viewModelScope.launch {
                _events.send(RegisterEvent.ShowError("Las contraseñas no coinciden"))
            }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                registerUseCase(
                    User(
                        id = null,
                        name = state.name,
                        username = state.name.lowercase(),
                        email = state.email,
                        password = state.password
                    )
                )
                _events.send(RegisterEvent.NavigateToHome)

            } catch (e: IllegalArgumentException) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message) }
                _events.send(RegisterEvent.ShowError(e.message ?: "Error de validación"))

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Error inesperado") }
                _events.send(RegisterEvent.ShowError("Error inesperado"))
            }
        }
    }
}