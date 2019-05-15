package com.lx.myapplication.utils

import android.content.Context
import android.content.SharedPreferences

class Cache(var context: Context) {
    private val NAME = "appdemo"
    private val MODE = Context.MODE_PRIVATE
    private lateinit var instance: SharedPreferences

    init {
        instance = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor)
    -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        instance.edit() {
            it.putBoolean(key, value)
        }
    }

    fun getBoolean(key: String): Boolean = instance.getBoolean(key, true)

    val IS_FIRST_START_APP = Pair("is_first_app", true)
}