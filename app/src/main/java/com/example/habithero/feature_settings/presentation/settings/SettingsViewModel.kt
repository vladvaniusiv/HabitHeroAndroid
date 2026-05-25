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
                    _uiState.update {
                        it.copy(
                            currentPasswordError = null,
                            newPasswordError = null,
                            confirmPasswordError = null
                        )
                    }

                    var hasError = false
                    val currentPassword = action.current
                    val newPassword = action.new
                    val confirmPassword = action.confirm

                    // Validación A: Menos de 8 caracteres
                    if (newPassword.length < 8) {
                        _uiState.update { it.copy(newPasswordError = "La contraseña debe tener al menos 8 caracteres") }
                        hasError = true
                    }

                    // Validación B: Igual que la anterior
                    if (newPassword == currentPassword && currentPassword.isNotEmpty()) {
                        _uiState.update { it.copy(newPasswordError = "La nueva contraseña no puede ser igual a la actual") }
                        hasError = true
                    }

                    // Validación C: No coinciden
                    if (newPassword != confirmPassword) {
                        _uiState.update { it.copy(confirmPasswordError = "Las contraseñas no coinciden") }
                        hasError = true
                    }

                    // Si alguna regla falló, frenamos la ejecución aquí
                    if (hasError) return@launch

                    // 2. Si todo está correcto, procesamos con el servidor
                    _uiState.update { it.copy(isLoading = true) }
                    val result = settingsRepository.updatePassword(currentPassword, newPassword)
                    _uiState.update { it.copy(isLoading = false) }

                    result.fold(
                        onSuccess = {
                            _events.send(SettingsEvent.ShowMessage("Contraseña actualizada exitosamente"))
                        },
                        onFailure = { e ->
                            // Si el servidor rechaza la contraseña actual por incorrecta, lo capturamos aquí
                            _uiState.update { it.copy(currentPasswordError = e.message ?: "Contraseña actual incorrecta") }
                        }
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
