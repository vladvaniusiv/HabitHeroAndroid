package com.example.habithero.data.remote.api

import com.example.habithero.data.remote.dto.AvailabilityResponseDto
import com.example.habithero.data.remote.dto.HabitDto
import com.example.habithero.data.remote.dto.HabitProgressDto
import com.example.habithero.data.remote.dto.LoginRequestDto
import com.example.habithero.data.remote.dto.LoginResponseDto
import com.example.habithero.data.remote.dto.RegisterRequestDto
import com.example.habithero.data.remote.dto.RegisterResponseDto
import com.example.habithero.data.remote.dto.UpdatePasswordRequestDto
import com.example.habithero.data.remote.dto.UpdateProfileDto
import com.example.habithero.data.remote.dto.UserDto
import com.example.habithero.data.remote.dto.WeeklyStatsRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface HabitHeroApi {

    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequestDto): RegisterResponseDto

    @GET("habits")
    suspend fun getHabits(
        @Header("Authorization") token: String
    ): List<HabitDto>

    @POST("habits")
    suspend fun createHabit(
        @Header("Authorization") token: String,
        @Body habit: HabitDto
    )

    @POST("stats/weekly")
    suspend fun getWeeklyStats(
        @Header("Authorization") token: String,
        @Body body: WeeklyStatsRequestDto
    ): List<HabitProgressDto>


    @GET("user/profile")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): UserDto

    @GET("user/check-username")
    suspend fun checkUsername(
        @Header("Authorization") token: String,
        @Query("username") username: String
    ): AvailabilityResponseDto

    @GET("user/check-email")
    suspend fun checkEmail(
        @Header("Authorization") token: String,
        @Query("email") email: String
    ): AvailabilityResponseDto

    @PUT("user/profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body body: UpdateProfileDto
    )

    @PUT("change-password")
    suspend fun updatePassword(
        @Header("Authorization") token: String,
        @Body body: UpdatePasswordRequestDto
    )

    @PATCH("habits/{habitId}/toggle")
    suspend fun toggleHabit(
        @Header("Authorization") token: String,
        @Path("habitId") habitId: Int,
        @Body request: com.example.habithero.data.remote.dto.ToggleHabitRequestDto
    ): retrofit2.Response<Unit>
}