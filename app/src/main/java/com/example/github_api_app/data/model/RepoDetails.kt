package com.example.github_api_app.data.model

import com.google.gson.annotations.SerializedName

data class RepoDetailsResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int
)

data class RepoDetailsUi(
    val name: String,
    val watchersCount: Int,
    val forksCount: Int
)