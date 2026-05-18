package com.example.habithero.data.fake

import com.example.habithero.core.domain.model.LoginResult
import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {

    private val registeredUsers = mutableListOf<User>()

    override suspend fun login(email: String, password: String): LoginResult {
        val user = registeredUsers.find { it.email == email && it.password == password }
        if (user == null) {
            throw IllegalArgumentException("Credenciales incorrectas")
        }
        return LoginResult(
            token = "fake-token",
            userId = user.id ?: 1,
            name = user.name,
            email = user.email
        )
    }

    override suspend fun register(user: User): LoginResult {
        if (registeredUsers.any { it.email == user.email }) {
            throw IllegalArgumentException("El usuario ya existe")
        }
        val newUser = user.copy(id = (registeredUsers.size + 1))
        registeredUsers.add(newUser)
        return LoginResult(
            token = "fake-token",
            userId = newUser.id!!,
            name = newUser.name,
            email = newUser.email
        )
    }
}
