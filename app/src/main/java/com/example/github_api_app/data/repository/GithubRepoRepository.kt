package com.example.github_api_app.data.repository

import com.example.github_api_app.data.api.ApiService
import com.example.github_api_app.data.model.State
import com.example.github_api_app.data.model.UserRepoResponse
import com.example.github_api_app.utils.safeApiCall

class GithubRepoRepository(private val apiService: ApiService) {

    suspend fun getUserRepos(userName: String): State<List<UserRepoResponse>> {
        return safeApiCall { apiService.getUserRepos(userName) }
    }
}