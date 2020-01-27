package com.usl.usl.utils

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson

@Suppress("DEPRECATION")
class LocalRepositories :Cv(){


    @Synchronized
    fun saveAppUser(ctx: Context?, user: AppUser?) {
        val jsonString = Gson().toJson(user)
        PreferenceManager.getDefaultSharedPreferences(ctx)
            .edit()
            .putString(super.PREFS_APP_USER, jsonString)
            .commit()
    }

    @Synchronized
    fun getAppUser(ctx: Context?): AppUser? {
        val jsonString = PreferenceManager.getDefaultSharedPreferences(ctx)
            .getString(super.PREFS_APP_USER, "")
        return if ("" == jsonString) null else Gson().fromJson(
            jsonString,
            AppUser::class.java
        )
    }

}