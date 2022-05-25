package com.example.courseapp.Model

import com.google.gson.annotations.SerializedName

class Course(
    @SerializedName("id") val id:Long,
    @SerializedName("name") val name:String,
    @SerializedName("price") val price:Int,
    @SerializedName("rating") val rating:Double,
    @SerializedName("description") val description:String,
    @SerializedName("author") val author:String,
    @SerializedName("imageUrl") val imageUrl:String,
    )