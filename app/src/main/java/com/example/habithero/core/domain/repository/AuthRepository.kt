package com.example.habithero.core.domain.repository

import com.example.habithero.core.domain.model.User

interface AuthRepository {

    suspend fun login(email: String, password: String)

    suspend fun register(user: User)
}
