package com.androidninjas.finalaveragingsimulator.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyNotYetValidException
import com.google.gson.GsonBuilder

object ModelPreferencesManager {
    lateinit var preferences: SharedPreferences
    private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"

    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> insert(`object` : T, key: String) {

        val jsonString = GsonBuilder().create().toJson(`object`)

        preferences.edit().putString(key, jsonString).apply()
    }

    fun remove(key: String) = preferences.edit().remove(key).apply()

    inline fun <reified T> get(key: String): T? {

        val value = preferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

}