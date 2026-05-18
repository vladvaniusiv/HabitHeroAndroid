package com.example.habithero.core.domain.usecase

import com.example.habithero.core.domain.model.LoginResult
import com.example.habithero.core.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): LoginResult {
        if (email.isBlank()) {
            throw IllegalArgumentException("El email no puede estar vacío")
        }
        if (password.isBlank()) {
            throw IllegalArgumentException("La contraseña no puede estar vacía")
        }

        return repository.login(email, password)
    }
}
