package com.example.github_api_app.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)