package com.example.habithero.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habithero.core.domain.usecase.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<LoginEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChanged -> {
                _uiState.value = _uiState.value.copy(
                    email = action.value,
                    errorMessage = null
                )
            }
            is LoginAction.OnPasswordChanged -> {
                _uiState.value = _uiState.value.copy(
                    password = action.value,
                    errorMessage = null
                )
            }
            LoginAction.OnLoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        val state = _uiState.value

        viewModelScope.launch {
            _uiState.value = state.copy(isLoading = true, errorMessage = null)

            try {
                loginUseCase(state.email, state.password)
                _events.send(LoginEvent.NavigateToHome)
            } catch (e: IllegalArgumentException) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
                _events.send(LoginEvent.ShowError(e.message ?: "Error de validación"))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Error inesperado"
                )
                _events.send(LoginEvent.ShowError("Error inesperado"))
            }
        }
    }
}
