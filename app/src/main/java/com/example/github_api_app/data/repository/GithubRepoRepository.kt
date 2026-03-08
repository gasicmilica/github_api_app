package com.example.github_api_app.data.repository

import com.example.github_api_app.data.api.ApiService
import com.example.github_api_app.data.response.RepoDetailsResponse
import com.example.github_api_app.data.response.State
import com.example.github_api_app.data.response.TagResponse
import com.example.github_api_app.data.response.UserRepoResponse
import com.example.github_api_app.utils.safeApiCall

class GithubRepoRepository(private val apiService: ApiService) {

    suspend fun getUserRepos(userName: String): State<List<UserRepoResponse>> {
        return safeApiCall { apiService.getUserRepos(userName) }
    }

    suspend fun getRepoDetails(userName: String, repo: String): State<RepoDetailsResponse> {
        return safeApiCall { apiService.getRepoDetails(userName, repo) }
    }

    suspend fun getRepoTags(userName: String, repo: String): State<List<TagResponse>> {
        return safeApiCall { apiService.getRepoTags(userName, repo) }
    }
}