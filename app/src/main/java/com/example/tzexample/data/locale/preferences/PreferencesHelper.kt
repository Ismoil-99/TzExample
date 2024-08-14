package com.example.tzexample.data.locale.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    private fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun getToken(): String =
        preferences.getString(ACCESS_TOKEN, null).toString()

    fun setToken(tokens: String) {
        preferences
            .edit()
            .putString(ACCESS_TOKEN, tokens)
            .apply()
    }


    fun isAuthored(): Boolean =
        preferences.contains(ACCESS_TOKEN) && !preferences.getString(ACCESS_TOKEN, null)
            .isNullOrEmpty()


    fun clearPreferences() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"

        const val PREFERENCES_FILE_NAME = "application_data"
    }
}