package com.devjethava.composeboilerplate.utils

import android.content.Context
import com.devjethava.composeboilerplate.network.response.UserResponse
import com.google.gson.Gson

class Preference(context: Context) {
    private val prefFileName = "com.devjethava.composeboilerplate"
    private val prfIsLogin = "IsLogin"
    private val prfUserResponse = "UserResponse"

    private val appLanguage = "appLanguage"
    private val prefResetLanguage = "prefResetLanguage"

    private val preference = context.getSharedPreferences(prefFileName, 0)

    private val gson: Gson = Gson()

    fun clearPreference() {
        preference.edit().clear().apply()
    }

    var isLogin: Boolean
        get() = preference.getBoolean(prfIsLogin, false)
        set(value) = preference.edit().putBoolean(prfIsLogin, value).apply()

    var userResponse: UserResponse
        get() = gson.fromJson(
            preference.getString(this.prfUserResponse, "").toString(),
            UserResponse::class.java
        )
        set(value) = preference.edit().putString(this.prfUserResponse, gson.toJson(value))
            .apply()

    var language: String
        get() = preference.getString(this.appLanguage, "en").toString()
        set(value) = preference.edit().putString(this.appLanguage, value).apply()

    var resetLanguage: Boolean
        get() = preference.getBoolean(prefResetLanguage, false)
        set(value) = preference.edit().putBoolean(prefResetLanguage, value).apply()
}