package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.UserDao
import com.example.habithero.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class SettingsLocalDataSource(
    private val dao: UserDao
) {
    fun getUserData(): Flow<UserEntity?> = dao.getUser()

    suspend fun saveUserData(user: UserEntity) = dao.insertUser(user)

    suspend fun updateProfile(name: String, username: String, email: String) {
        dao.updateProfile(name, username, email)
    }
}