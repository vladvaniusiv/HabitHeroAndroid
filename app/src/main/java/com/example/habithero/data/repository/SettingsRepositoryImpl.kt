package com.example.habithero.data.repository

import com.example.habithero.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SettingsRepositoryImpl : SettingsRepository {

    private var avatarCache: ByteArray = ByteArray(0)

    override suspend fun uploadAvatar(userId: Int, filePath: String) {
        // Simulación: en una app real subirías el archivo
        avatarCache = ByteArray(0)
    }

    override fun getAvatar(userId: Int): Flow<ByteArray> {
        return flowOf(avatarCache)
    }
}
