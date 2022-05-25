package com.example.courseapp.Query

import android.text.Editable
import com.example.courseapp.Model.Users
import retrofit2.http.POST
import retrofit2.http.Query

interface UserInterface {
    @POST("api/user/checkLoginAndPassword")
    fun getStatus(
        @Query("email") email: String,
        @Query("password") password: String
    ): Users
}