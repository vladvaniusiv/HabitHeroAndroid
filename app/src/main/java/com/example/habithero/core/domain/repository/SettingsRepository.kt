package com.example.habithero.core.domain.repository

import com.example.habithero.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun uploadAvatar(userId: Int, filePath: String)

    fun getAvatar(userId: Int): Flow<ByteArray>

    fun getUserData(): Flow<User>
    suspend fun checkUsernameAvailable(username: String): Boolean
    suspend fun checkEmailAvailable(email: String): Boolean
    suspend fun updateProfile(name: String, username: String, email: String): Result<Unit>
    suspend fun updatePassword(current: String, new: String): Result<Unit>
}
