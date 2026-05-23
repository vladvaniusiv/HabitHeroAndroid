package com.example.habithero.data.fake

import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSettingsRepository : SettingsRepository {

    override suspend fun uploadAvatar(userId: Int, filePath: String) {
        // No hace nada en el entorno fake
    }

    override fun getAvatar(userId: Int): Flow<ByteArray> {
        return flowOf(ByteArray(0))
    }

    override fun getUserData(): Flow<User> {
        return flowOf(
            User(
                id = 1,
                name = "Vlad Vaniusiv",
                username = "vladvan",
                email = "vlad@gmail.com",
                password = ""
            )
        )
    }

    override suspend fun checkUsernameAvailable(username: String): Boolean {
        // En el entorno simulado, aceptamos cualquier nombre excepto "ocupado"
        return username != "ocupado"
    }

    override suspend fun checkEmailAvailable(email: String): Boolean {
        // En el entorno simulado, aceptamos cualquier correo excepto uno ya registrado
        return email != "test@gmail.com"
    }

    override suspend fun updateProfile(name: String, username: String, email: String): Result<Unit> {
        // Simula un guardado exitoso
        return Result.success(Unit)
    }

    override suspend fun updatePassword(current: String, new: String): Result<Unit> {
        // Simula una actualización de contraseña exitosa
        return Result.success(Unit)
    }
}