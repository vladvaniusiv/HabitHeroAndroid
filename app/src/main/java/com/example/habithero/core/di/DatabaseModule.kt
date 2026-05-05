package com.example.habithero.core.di

import androidx.room.Room
import com.example.habithero.data.local.database.HabitHeroDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            HabitHeroDatabase::class.java,
            "habithero.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<HabitHeroDatabase>().habitDao() }
    single { get<HabitHeroDatabase>().habitProgressDao() }
    single { get<HabitHeroDatabase>().userDao() }
}
