package com.example.github_api_app.data.model

data class TagResponse (
    val name: String,
    val commit: Commit
)

data class Commit (
    val sha: String
)

data class TagUi (
    val name: String,
    val sha: String
)