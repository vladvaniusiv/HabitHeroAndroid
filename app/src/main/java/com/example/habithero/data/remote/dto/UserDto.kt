package com.example.habithero.data.remote.dto

data class UserDto(
    val id: Int?,
    val name: String,
    val username: String,
    val email: String,
    val password: String
)

data class AvailabilityResponseDto(
    val isAvailable: Boolean
)

data class UpdateProfileDto(
    val name: String,
    val username: String,
    val email: String
)

data class UpdatePasswordRequestDto(
    val current: String,
    val new: String
)