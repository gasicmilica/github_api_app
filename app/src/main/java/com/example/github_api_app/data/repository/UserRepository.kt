package com.example.github_api_app.data.repository

import com.example.github_api_app.data.api.ApiService
import com.example.github_api_app.data.model.State
import com.example.github_api_app.data.model.User
import com.example.github_api_app.utils.safeApiCall

class UserRepository(private val apiService: ApiService) {

    suspend fun getUserInfo(userName: String): State<User> {
        return safeApiCall { apiService.getUserInfo(userName) }
    }

}