package com.example.github_api_app.data.repository

import com.example.github_api_app.data.api.ApiService
import com.example.github_api_app.data.model.User

class UserRepository(private val apiService: ApiService) {

    suspend fun getUserInfo(userName: String): User {
        val response = apiService.getUserInfo(userName)

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception(response.errorBody().toString())
        }
    }

}