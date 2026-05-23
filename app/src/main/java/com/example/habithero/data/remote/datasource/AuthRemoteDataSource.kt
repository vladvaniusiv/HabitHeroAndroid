package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.LoginRequestDto
import com.example.habithero.data.remote.dto.LoginResponseDto
import com.example.habithero.data.remote.dto.RegisterRequestDto
import com.example.habithero.data.remote.dto.RegisterResponseDto
import com.example.habithero.data.remote.dto.UserDto

class AuthRemoteDataSource(
    private val api: HabitHeroApi
) {

    suspend fun login(email: String, password: String): LoginResponseDto {
        return api.login(LoginRequestDto(email, password))
    }

    suspend fun register(name: String, email: String, password: String): RegisterResponseDto {
        return api.register(RegisterRequestDto(name, email, password))
    }
}
