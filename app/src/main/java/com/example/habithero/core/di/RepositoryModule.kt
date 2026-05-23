package com.example.habithero.core.di

import com.example.habithero.core.domain.repository.AuthRepository
import com.example.habithero.core.domain.repository.HabitRepository
import com.example.habithero.core.domain.repository.SettingsRepository
import com.example.habithero.core.domain.repository.StatsRepository
import com.example.habithero.data.fake.FakeAuthRepository
import com.example.habithero.data.fake.FakeHabitRepository
import com.example.habithero.data.fake.FakeSettingsRepository
import com.example.habithero.data.fake.FakeStatsRepository
import com.example.habithero.data.local.datasource.SettingsLocalDataSource
import com.example.habithero.data.remote.datasource.SettingsRemoteDataSource
import com.example.habithero.data.repository.AuthRepositoryImpl
import com.example.habithero.data.repository.HabitRepositoryImpl
import com.example.habithero.data.repository.SettingsRepositoryImpl
import com.example.habithero.data.repository.StatsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
/*
    single<AuthRepository> { FakeAuthRepository() }
    single<HabitRepository> { FakeHabitRepository() }
    single<StatsRepository> { FakeStatsRepository() }
    single<SettingsRepository> { FakeSettingsRepository() }
*/
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get(),get()) }
    single<HabitRepository> { HabitRepositoryImpl(get(), get(), get()) }
    single<StatsRepository> { StatsRepositoryImpl(get(), get(), get()) }
    single<SettingsLocalDataSource> { SettingsLocalDataSource(get()) }
    single<SettingsRemoteDataSource> { SettingsRemoteDataSource(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get(), get(), get()) }

}
