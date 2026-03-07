package com.example.github_api_app.data.api

import com.example.github_api_app.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{user_name}")
    suspend fun getUserInfo(@Path("user_name") userName: String): Response<User>
}