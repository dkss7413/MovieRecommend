package com.example.movierecommender.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SaveSharedPreference {
    companion object{
        var PREF_USER_NAME = "username"

        private fun getSharedPreferences(context: Context?): SharedPreferences{
            return androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun setUserName(context: Context?, userName: String){
            val editor = getSharedPreferences(context).edit()
            editor.putString(PREF_USER_NAME, userName)
            editor.commit()
        }

        fun getUserName(context: Context?): String?{
            return getSharedPreferences(context).getString(PREF_USER_NAME, "")
        }

        fun clearUserName(context: Context?){
            val editor = getSharedPreferences(context).edit()
            editor.clear()
            editor.commit()
        }
    }
}