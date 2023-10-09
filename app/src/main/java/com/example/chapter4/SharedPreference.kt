package com.example.chapter4

import android.content.Context
import androidx.core.content.edit
import android.content.Context.MODE_PRIVATE



class SharedPreference(context: Context) {
    private val sharedPreferencesName = "SharedPreference"
    private var preference = context.getSharedPreferences(sharedPreferencesName, 0)

    var isGrid: Boolean
        set(value) {
            preference.edit {
                putBoolean("IS_GRID", value)
            }
        }
        get() = preference.getBoolean("IS_GRID", false)

}