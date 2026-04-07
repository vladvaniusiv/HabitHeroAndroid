package com.example.habithero.core.di

import com.example.habithero.core.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { CreateHabitUseCase(get()) }
    factory { GetWeeklyStatsUseCase(get()) }
}
