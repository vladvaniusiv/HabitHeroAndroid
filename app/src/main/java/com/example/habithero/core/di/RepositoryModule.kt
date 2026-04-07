package com.example.habithero.core.di

import com.example.habithero.data.fake.*
import com.example.habithero.core.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {

    single<AuthRepository> { FakeAuthRepository() }
    single<HabitRepository> { FakeHabitRepository() }
    single<StatsRepository> { FakeStatsRepository() }
    single<SettingsRepository> { FakeSettingsRepository() }
}
