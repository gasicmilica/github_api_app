package com.example.github_api_app.data.response

import com.google.gson.annotations.SerializedName

data class UserRepoResponse (
    @SerializedName("name")
    val name: String,
    @SerializedName("open_issues_count")
    val openIssueCount: Int
)