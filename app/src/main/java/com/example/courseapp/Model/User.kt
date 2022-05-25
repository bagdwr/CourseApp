package com.example.courseapp.Model

import com.google.gson.annotations.SerializedName
import java.util.*

class User(
    @SerializedName("id") val id:Long,
    @SerializedName("fullname") val fullname:String,
    @SerializedName("email") val email:String,
    @SerializedName("birthday") val birthday:String,
    @SerializedName("password") val password:String);