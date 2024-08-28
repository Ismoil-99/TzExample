package com.example.tzexample.data.locale.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    fun remove(key: String) {
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

    fun getTypeAnnounced():Int = preferences.getInt(TYPE_ANNOUNCED,0)

    fun setTypeAnnounced(type:Int){
        preferences
            .edit()
            .putInt(TYPE_ANNOUNCED, type)
            .apply()
    }

    fun setNumber(num:String){
        preferences.edit().putString(TELL_USER,num).apply()
    }

    fun getTell():String = preferences.getString(TELL_USER,"").toString()

    fun getBack():Int = preferences.getInt(BACK_FROM_PROFILE,0)

    fun setBackProfile(back:Int){
        preferences.edit().putInt(BACK_FROM_PROFILE,back).apply()
    }


    fun isAuthored(): Boolean =
        preferences.contains(ACCESS_TOKEN) && !preferences.getString(ACCESS_TOKEN, null)
            .isNullOrEmpty()


    fun clearPreferences() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val BACK_FROM_PROFILE = "back_from_profile"
        private const val TELL_USER = "tel_user"
        private const val TYPE_ANNOUNCED = "type_announced"

        const val PREFERENCES_FILE_NAME = "application_data"
    }
}