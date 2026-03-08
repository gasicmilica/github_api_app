package com.example.github_api_app.data.mapper

import com.example.github_api_app.data.response.UserResponse
import com.example.github_api_app.model.UserUi

fun UserResponse.toUserUi(): UserUi {
    return UserUi(
        name = this.name,
        avatarUrl = this.avatarUrl
    )
}