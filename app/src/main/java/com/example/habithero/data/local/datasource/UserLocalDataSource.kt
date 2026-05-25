package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.UserDao
import com.example.habithero.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserLocalDataSource(
    private val dao: UserDao
) {

    fun getUser(userId: Int): Flow<UserEntity?> {
        return dao.getUserById(userId)
    }

    suspend fun saveUser(user: UserEntity) {
        dao.insertUser(user)
    }
}
