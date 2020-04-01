package com.example.movierecommender.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SaveSharedPreference {
    companion object{

        var PREF_USER_ID = ""
        var PREF_NICKNAME =  ""

        private fun getSharedPreferences(context: Context?): SharedPreferences{
            return androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun setUser(context: Context?, userId: String, nickname: String){
            val editor = getSharedPreferences(context).edit()
            editor.putString(PREF_USER_ID, userId)
            editor.putString(PREF_NICKNAME, nickname)
            editor.commit()
        }

        fun getUserId(context: Context?): String?{
            return getSharedPreferences(context).getString(PREF_USER_ID, "")
        }

        fun getNickname(context: Context?): String?{
            return getSharedPreferences(context).getString(PREF_NICKNAME, "")
        }

        fun clearUser(context: Context?){
            val editor = getSharedPreferences(context).edit()
            editor.clear()
            editor.commit()
        }
    }
}