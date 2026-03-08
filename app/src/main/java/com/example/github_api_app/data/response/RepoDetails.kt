package com.example.github_api_app.data.response

import com.google.gson.annotations.SerializedName

data class RepoDetailsResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
)