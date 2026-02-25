package com.example.habithero.core.domain.usecase

import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(user: User) {
        if (user.name.isBlank()) {
            throw IllegalArgumentException("El nombre no puede estar vacío")
        }
        if (user.username.isBlank()) {
            throw IllegalArgumentException("El nombre de usuario no puede estar vacío")
        }
        if (user.email.isBlank()) {
            throw IllegalArgumentException("El email no puede estar vacío")
        }
        if (user.password.length < 4) {
            throw IllegalArgumentException("La contraseña debe tener al menos 4 caracteres")
        }

        repository.register(user)
    }
}
