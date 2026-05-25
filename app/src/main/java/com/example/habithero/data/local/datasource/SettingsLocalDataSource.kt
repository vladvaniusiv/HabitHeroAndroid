package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.UserDao
import com.example.habithero.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class SettingsLocalDataSource(
    private val dao: UserDao
) {
    fun getUserData(userId: Int): Flow<UserEntity?> = dao.getUserById(userId)

    suspend fun saveUserData(user: UserEntity) = dao.insertUser(user)

    suspend fun updateProfile(id: Int, name: String, username: String, email: String) {
        dao.updateProfile(id, name, username, email)
    }
}