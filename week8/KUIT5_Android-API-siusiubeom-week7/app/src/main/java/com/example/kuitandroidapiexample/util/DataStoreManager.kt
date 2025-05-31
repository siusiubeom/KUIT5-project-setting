package com.example.kuitandroidapiexample.util

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore(name = "my_datastore")
object DataStoreManager {

    suspend fun saveValue(context: Context, key: String, value: String) {
        val EXAMPLE_KEY = stringPreferencesKey(key)
        context.datastore.edit { preferences ->
                preferences[EXAMPLE_KEY] = value
        }
    }
    fun getValue(context: Context, key: String): Flow<String?> {
        val EXAMPLE_KEY = stringPreferencesKey(key)
        return context.datastore.data.map { preferences ->
            preferences[EXAMPLE_KEY]
        }
    }
    suspend fun deleteValue(context: Context, key: String) {
        val EXAMPLE_KEY = stringPreferencesKey(key)
        context.datastore.edit { preferences ->
            preferences.remove(EXAMPLE_KEY)
        }
    }
}