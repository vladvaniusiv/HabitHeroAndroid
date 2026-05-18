package com.example.habithero.core.di

import com.example.habithero.data.local.datastore.SessionDataStore
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

val dataStoreModule = module {
    single { SessionDataStore(androidContext()) }
}
