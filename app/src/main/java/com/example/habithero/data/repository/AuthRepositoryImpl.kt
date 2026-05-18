package com.example.habithero.data.repository

import com.example.habithero.core.domain.model.LoginResult
import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.AuthRepository
import com.example.habithero.data.local.datastore.SessionDataStore
import com.example.habithero.data.local.datasource.UserLocalDataSource
import com.example.habithero.data.mapper.toEntity
import com.example.habithero.data.remote.datasource.AuthRemoteDataSource

class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    private val local: UserLocalDataSource,
    private val session: SessionDataStore
) : AuthRepository {

    override suspend fun login(email: String, password: String): LoginResult {
        val response = remote.login(email, password)

        // Guardar token en DataStore
        session.saveToken(response.token)
        session.saveUserId(response.userId)
        session.setLoggedIn(true)

        // Guardar usuario mínimo en Room
        val user = User(
            id = response.userId,
            name = response.name,
            username = response.name.lowercase(),
            email = response.email,
            password = "" // nunca guardes password
        )

        local.saveUser(user.toEntity())
        return LoginResult(
            token = response.token,
            userId = response.userId,
            name = response.name,
            email = response.email
        )
    }

    override suspend fun register(user: User): LoginResult {
        val response = remote.register(user.name, user.email, user.password)

        session.saveToken(response.token)
        session.saveUserId(response.userId)
        session.setLoggedIn(true)

        local.saveUser(
            User(
                id = response.userId,
                name = response.name,
                username = response.name.lowercase(),
                email = response.email,
                password = ""
            ).toEntity()
        )

        return LoginResult(
            token = response.token,
            userId = response.userId,
            name = response.name,
            email = response.email
        )
    }
}