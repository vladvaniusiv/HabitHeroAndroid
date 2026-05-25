package com.example.habithero.core.di

import com.example.habithero.data.local.datasource.HabitLocalDataSource
import com.example.habithero.data.local.datasource.StatsLocalDataSource
import com.example.habithero.data.local.datasource.UserLocalDataSource
import com.example.habithero.data.remote.datasource.AuthRemoteDataSource
import com.example.habithero.data.remote.datasource.HabitRemoteDataSource
import com.example.habithero.data.remote.datasource.StatsRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    // Remote
    single { AuthRemoteDataSource(get()) }
    single { HabitRemoteDataSource(get()) }
    single { StatsRemoteDataSource(get()) }

    // Local
    single { HabitLocalDataSource(get(),get()) }
    single { StatsLocalDataSource(get()) }
    single { UserLocalDataSource(get()) }
}
