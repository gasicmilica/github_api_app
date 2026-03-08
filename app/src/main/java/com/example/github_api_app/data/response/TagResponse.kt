package com.example.github_api_app.data.response

data class TagResponse (
    val name: String,
    val commit: Commit
)

data class Commit (
    val sha: String
)