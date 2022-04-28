package com.example.courseapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {
    private var preference:SharedPreferences? = null

    fun getSharedPreference(app:Application):SharedPreferences{
        if (preference==null){
            preference=app.getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE)
        }
        return preference!!
    }

    const val KEY="user_client"

}