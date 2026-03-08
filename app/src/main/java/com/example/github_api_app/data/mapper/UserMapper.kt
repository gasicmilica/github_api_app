package com.example.github_api_app.data.mapper

import com.example.github_api_app.data.model.UserResponse
import com.example.github_api_app.data.model.UserUi

fun UserResponse.toUserUi(): UserUi {
    return UserUi(
        name = this.name,
        avatarUrl = this.avatarUrl
    )
}