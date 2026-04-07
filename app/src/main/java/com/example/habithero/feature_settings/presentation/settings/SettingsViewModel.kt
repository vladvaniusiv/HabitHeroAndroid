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
            // Datos fake por ahora
            _uiState.value = SettingsUiState(
                name = "Vlad Vaniusiv",
                userName = "@vladvan",
                email = "vlad@gmail.com",
                isLoading = false
            )
        }
    }

    fun onAction(action: SettingsAction) {
        when (action) {

            is SettingsAction.OnNameChanged ->
                _uiState.update { it.copy(name = action.name) }

            is SettingsAction.OnUserNameChanged ->
                _uiState.update { it.copy(userName = action.userName) }

            is SettingsAction.OnEmailChanged ->
                _uiState.update { it.copy(email = action.email) }

            is SettingsAction.OnPasswordChange -> {
                viewModelScope.launch {
                    if (action.new != action.confirm) {
                        _events.send(SettingsEvent.ShowMessage("Las contraseñas no coinciden"))
                    } else {
                        _events.send(SettingsEvent.ShowMessage("Contraseña actualizada"))
                    }
                }
            }

            SettingsAction.OnAvatarClicked ->
                viewModelScope.launch { _events.send(SettingsEvent.ShowAvatarPicker) }

            SettingsAction.OnBackClicked ->
                viewModelScope.launch { _events.send(SettingsEvent.NavigateBack) }

            SettingsAction.OnNavigateHome ->
                viewModelScope.launch { _events.send(SettingsEvent.NavigateToHome) }

            SettingsAction.OnNavigateStats ->
                viewModelScope.launch { _events.send(SettingsEvent.NavigateToStats) }
        }
    }
}
