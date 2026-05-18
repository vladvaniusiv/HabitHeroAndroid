package com.example.habithero.data.remote.dto

data class LoginResponseDto(
    val token: String,
    val userId: Int,
    val name: String,
    val email: String
)