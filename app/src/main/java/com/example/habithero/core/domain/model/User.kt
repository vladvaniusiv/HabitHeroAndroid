package com.example.habithero.core.domain.model

data class User(
    val id: Int? = null,
    val name: String,
    val username: String,
    val email: String,
    val password: String
)
