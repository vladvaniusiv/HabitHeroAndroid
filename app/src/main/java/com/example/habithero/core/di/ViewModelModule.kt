package com.example.habithero.core.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.habithero.feature_auth.presentation.login.LoginViewModel
import com.example.habithero.feature_auth.presentation.signup.RegisterViewModel
import com.example.habithero.feature_home.presentation.home.HomeViewModel
import com.example.habithero.feature_settings.presentation.settings.SettingsViewModel
import com.example.habithero.feature_stats.presentation.stats.StatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val viewModelModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { StatsViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}
