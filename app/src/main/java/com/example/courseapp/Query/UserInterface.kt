package com.example.courseapp.Query

import android.text.Editable
import com.example.courseapp.Model.Users
import retrofit2.http.POST
import retrofit2.http.Query

interface UserInterface {
    @POST("api/user/login")
    fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Users

    @POST("api/user/register")
    fun registerUser(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("fullname") fullname:String,
        @Query("birthday") birthday: String
    ): Boolean



}