package com.example.habithero.data.fake

import com.example.habithero.core.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSettingsRepository : SettingsRepository {

    override suspend fun uploadAvatar(userId: Int, filePath: String) {

    }

    override fun getAvatar(userId: Int): Flow<ByteArray> {
        return flowOf(ByteArray(0))
    }
}
