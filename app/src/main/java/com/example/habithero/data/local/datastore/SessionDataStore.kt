package com.example.habithero.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.sessionDataStore by preferencesDataStore("session_prefs")

class SessionDataStore(private val context: Context) {

    companion object {
        private val TOKEN = stringPreferencesKey("token")
        private val USER_ID = intPreferencesKey("user_id")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val token: Flow<String?> = context.sessionDataStore.data.map { it[TOKEN] }
    val userId: Flow<Int?> = context.sessionDataStore.data.map { it[USER_ID] }
    val isLoggedIn: Flow<Boolean> = context.sessionDataStore.data.map { it[IS_LOGGED_IN] ?: false }

    suspend fun saveToken(value: String) {
        context.sessionDataStore.edit { it[TOKEN] = value }
    }

    suspend fun saveUserId(value: Int) {
        context.sessionDataStore.edit { it[USER_ID] = value }
    }

    suspend fun setLoggedIn(value: Boolean) {
        context.sessionDataStore.edit { it[IS_LOGGED_IN] = value }
    }

    suspend fun clearSession() {
        context.sessionDataStore.edit { it.clear() }
    }
}