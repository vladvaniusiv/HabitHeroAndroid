package com.example.habithero.core.domain.repository

import com.example.habithero.core.domain.model.LoginResult
import com.example.habithero.core.domain.model.User

interface AuthRepository {

    suspend fun login(email: String, password: String): LoginResult

    suspend fun register(user: User): LoginResult
}
