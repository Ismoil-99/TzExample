package com.example.tzexample.presentation.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = this.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()
    }

    companion object {
        lateinit var sharedPreferences: SharedPreferences
        lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    }
}