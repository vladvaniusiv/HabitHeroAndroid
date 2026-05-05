package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.UserDto

class AuthRemoteDataSource(
    private val api: HabitHeroApi
) {

    suspend fun login(email: String, password: String) {
        val body = UserDto(
            id = null,
            name = "",
            email = email,
            password = password
        )
        api.login(body)
    }

    suspend fun register(user: UserDto) {
        api.register(user)
    }
}
