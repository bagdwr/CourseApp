package com.example.courseapp.Query

import android.text.Editable
import com.example.courseapp.Model.User
import retrofit2.http.POST
import retrofit2.http.Query

interface UserInterface {
    @POST("api/checkLoginAndPassword")
    fun getStatus(
        @Query("email") email: Editable,
        @Query("password") password: Editable
    ): User
}