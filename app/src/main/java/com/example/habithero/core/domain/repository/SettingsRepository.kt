package com.example.habithero.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun uploadAvatar(userId: Int, filePath: String)

    fun getAvatar(userId: Int): Flow<ByteArray>
}
