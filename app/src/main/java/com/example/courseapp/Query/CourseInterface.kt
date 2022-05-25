package com.example.courseapp.Query

import com.example.courseapp.Model.Course
import com.example.courseapp.Model.Users
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CourseInterface {
    @GET("api/course/allCourses")
    fun getAllCourses(): List<Course>

}