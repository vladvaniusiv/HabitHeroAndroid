package com.example.habithero.data.fake

import com.example.habithero.core.domain.model.User
import com.example.habithero.core.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {

    private val registeredUsers = mutableListOf<User>()

    override suspend fun login(email: String, password: String) {
        val user = registeredUsers.find { it.email == email && it.password == password }
        if (user == null) {
            throw IllegalArgumentException("Credenciales incorrectas")
        }
    }

    override suspend fun register(user: User) {
        if (registeredUsers.any { it.email == user.email }) {
            throw IllegalArgumentException("El usuario ya existe")
        }
        registeredUsers.add(user)
    }
}
