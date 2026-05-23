package com.example.habithero.data.remote.api

import com.example.habithero.data.remote.dto.HabitDto
import com.example.habithero.data.remote.dto.HabitProgressDto
import com.example.habithero.data.remote.dto.LoginRequestDto
import com.example.habithero.data.remote.dto.LoginResponseDto
import com.example.habithero.data.remote.dto.RegisterRequestDto
import com.example.habithero.data.remote.dto.RegisterResponseDto
import com.example.habithero.data.remote.dto.UserDto
import com.example.habithero.data.remote.dto.WeeklyStatsRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HabitHeroApi {

    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequestDto): RegisterResponseDto

    @GET("habits/{userId}")
    suspend fun getHabits(@Path("userId") userId: Int): List<HabitDto>

    @POST("habits")
    suspend fun createHabit(@Body habit: HabitDto)

    @POST("stats/weekly")
    suspend fun getWeeklyStats(@Body body: WeeklyStatsRequestDto): List<HabitProgressDto>

}