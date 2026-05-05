package com.example.habithero.core.di

import com.example.habithero.data.remote.api.HabitHeroApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<HabitHeroApi> { get<Retrofit>().create(HabitHeroApi::class.java) }
}
