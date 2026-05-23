package com.example.habithero.data.local.datasource

import com.example.habithero.data.local.dao.UserDao
import com.example.habithero.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserLocalDataSource(
    private val dao: UserDao
) {

    fun getUser(): Flow<UserEntity?> {
        return dao.getUser()
    }

    suspend fun saveUser(user: UserEntity) {
        dao.insertUser(user)
    }
}
