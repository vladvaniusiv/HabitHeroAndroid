package com.example.habithero.data.remote.datasource

import com.example.habithero.data.remote.api.HabitHeroApi
import com.example.habithero.data.remote.dto.*

class SettingsRemoteDataSource(
    private val api: HabitHeroApi
) {
    suspend fun getUserProfile(token: String): UserDto =
        api.getUserProfile("Bearer $token")

    suspend fun checkUsername(token: String, username: String): AvailabilityResponseDto =
        api.checkUsername("Bearer $token", username)

    suspend fun checkEmail(token: String, email: String): AvailabilityResponseDto =
        api.checkEmail("Bearer $token", email)

    suspend fun updateProfile(token: String, dto: UpdateProfileDto) =
        api.updateProfile("Bearer $token", dto)

    suspend fun updatePassword(token: String, dto: UpdatePasswordRequestDto) =
        api.updatePassword("Bearer $token", dto)
}