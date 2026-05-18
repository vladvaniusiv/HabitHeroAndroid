package com.example.habithero.core.domain.model

data class LoginResult(
    val token: String,
    val userId: Int,
    val name: String,
    val email: String
)
