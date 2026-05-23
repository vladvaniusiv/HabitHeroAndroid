package com.example.habithero.feature_settings.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habithero.core.domain.repository.SettingsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<SettingsEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            settingsRepository.getUserData()
                .catch { e -> _uiState.update { it.copy(isLoading = false, errorMessage = e.message) } }
                .collect { user ->
                    _uiState.update { it.copy(
                        name = user.name,
                        userName = user.username,
                        email = user.email,
                        isLoading = false
                    ) }
                }
        }
    }

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.OnNameChanged -> _uiState.update { it.copy(name = action.name) }

            is SettingsAction.OnUserNameChanged -> {
                _uiState.update { it.copy(userName = action.userName, userNameError = null) }
                viewModelScope.launch {
                    val isAvailable = settingsRepository.checkUsernameAvailable(action.userName)
                    if (!isAvailable) {
                        _uiState.update { it.copy(userNameError = "Este nombre de usuario ya está en uso") }
                    }
                }
            }

            is SettingsAction.OnEmailChanged -> {
                _uiState.update { it.copy(email = action.email, emailError = null) }
                viewModelScope.launch {
                    val isAvailable = settingsRepository.checkEmailAvailable(action.email)
                    if (!isAvailable) {
                        _uiState.update { it.copy(emailError = "Este correo ya está registrado") }
                    }
                }
            }

            SettingsAction.OnSaveProfile -> {
                if (_uiState.value.userNameError != null || _uiState.value.emailError != null) return
                viewModelScope.launch {
                    _uiState.update { it.copy(isLoading = true) }
                    val result = settingsRepository.updateProfile(
                        name = _uiState.value.name,
                        username = _uiState.value.userName,
                        email = _uiState.value.email
                    )
                    _uiState.update { it.copy(isLoading = false) }
                    result.fold(
                        onSuccess = { _events.send(SettingsEvent.ShowMessage("Perfil actualizado correctamente")) },
                        onFailure = { e -> _events.send(SettingsEvent.ShowMessage(e.message ?: "Error al actualizar")) }
                    )
                }
            }

            is SettingsAction.OnPasswordChange -> {
                viewModelScope.launch {
                    if (action.new != action.confirm) {
                        _events.send(SettingsEvent.ShowMessage("Las contraseñas no coinciden"))
                        return@launch
                    }
                    _uiState.update { it.copy(isLoading = true) }
                    val result = settingsRepository.updatePassword(action.current, action.new)
                    _uiState.update { it.copy(isLoading = false) }
                    result.fold(
                        onSuccess = { _events.send(SettingsEvent.ShowMessage("Contraseña actualizada exitosamente")) },
                        onFailure = { e -> _events.send(SettingsEvent.ShowMessage(e.message ?: "Error al actualizar contraseña")) }
                    )
                }
            }

            SettingsAction.OnAvatarClicked -> viewModelScope.launch { _events.send(SettingsEvent.ShowAvatarPicker) }
            SettingsAction.OnBackClicked -> viewModelScope.launch { _events.send(SettingsEvent.NavigateBack) }
            SettingsAction.OnNavigateHome -> viewModelScope.launch { _events.send(SettingsEvent.NavigateToHome) }
            SettingsAction.OnNavigateStats -> viewModelScope.launch { _events.send(SettingsEvent.NavigateToStats) }
        }
    }
}
