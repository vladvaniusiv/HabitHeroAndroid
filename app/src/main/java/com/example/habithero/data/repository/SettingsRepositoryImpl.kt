package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.SettingsRepository
import com.example.habithero.data.local.datasource.SettingsLocalDataSource
import com.example.habithero.data.local.datastore.SessionDataStore
import com.example.habithero.data.mapper.toDomain
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.SettingsRemoteDataSource
import com.example.habithero.data.remote.dto.UpdatePasswordRequestDto
import com.example.habithero.data.remote.dto.UpdateProfileDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val sessionDataStore: SessionDataStore,
    private val local: SettingsLocalDataSource,
    private val remote: SettingsRemoteDataSource
) : SettingsRepository {

    private var avatarCache: ByteArray = ByteArray(0)

    override suspend fun uploadAvatar(userId: Int, filePath: String) {
        avatarCache = ByteArray(0)
    }

    override fun getAvatar(userId: Int): Flow<ByteArray> {
        return flowOf(avatarCache)
    }

    override fun getUserData(): Flow<User> = flow {
        // 1. Emitir local inmediatamente (usamos filterNotNull por si la BD está vacía al inicio)
        emitAll(
            local.getUserData()
                .map { entity ->
                    entity?.toDomain() ?: User(name = "", username = "", email = "", password = "")
                }
        )

        try {
            val token = sessionDataStore.token.first() ?: ""
            if (token.isNotEmpty()) {
                // 2. Obtener remoto fresco
                val remoteUser = remote.getUserProfile(token)
                // 3. Guardar en local cacheando la info
                local.saveUserData(remoteUser.toEntity())
                // 4. Emitir el último cambio de la red
                emit(remoteUser.toDomain())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun checkUsernameAvailable(username: String): Boolean {
        return try {
            val token = sessionDataStore.token.first() ?: ""
            if (token.isNotEmpty()) {
                remote.checkUsername(token, username).isAvailable
            } else false
        } catch (e: Exception) {
            false // Si el servidor falla, mejor prevenir el registro bloqueando el botón
        }
    }

    override suspend fun checkEmailAvailable(email: String): Boolean {
        return try {
            val token = sessionDataStore.token.first() ?: ""
            if (token.isNotEmpty()) {
                remote.checkEmail(token, email).isAvailable
            } else false
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateProfile(name: String, username: String, email: String): Result<Unit> {
        return try {
            val token = sessionDataStore.token.first() ?: ""
            if (token.isNotEmpty()) {
                remote.updateProfile(token, UpdateProfileDto(name, username, email))
                local.updateProfile(name, username, email)
                Result.success(Unit)
            } else {
                Result.failure(Exception("No se encontró una sesión activa"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePassword(current: String, new: String): Result<Unit> {
        return try {
            val token = sessionDataStore.token.first() ?: ""
            if (token.isNotEmpty()) {
                remote.updatePassword(token, UpdatePasswordRequestDto(current, new))
                Result.success(Unit)
            } else {
                Result.failure(Exception("No se encontró una sesión activa"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}