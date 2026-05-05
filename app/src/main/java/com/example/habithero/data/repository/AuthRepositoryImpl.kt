package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.AuthRepository
import com.example.habithero.data.local.datasource.UserLocalDataSource
import com.example.habithero.data.mapper.toDomain
import com.example.habithero.data.mapper.toDto
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.AuthRemoteDataSource

class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    private val local: UserLocalDataSource
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        remote.login(email, password)

        // Guardamos usuario mínimo en local (mock realista)
        val user = User(
            id = 1,
            name = email.substringBefore("@"),
            username = email.substringBefore("@"),
            email = email,
            password = password
        )

        local.saveUser(user.toEntity())
    }

    override suspend fun register(user: User) {
        remote.register(user.toDto())
        local.saveUser(user.toEntity())
    }
}
