package com.example.github_api_app.data.repository

import com.example.github_api_app.data.api.ApiService
import com.example.github_api_app.data.response.State
import com.example.github_api_app.data.response.UserResponse
import com.example.github_api_app.utils.safeApiCall

class UserRepository(private val apiService: ApiService) {

    suspend fun getUserInfo(userName: String): State<UserResponse> {
        return safeApiCall { apiService.getUserInfo(userName) }
    }

}