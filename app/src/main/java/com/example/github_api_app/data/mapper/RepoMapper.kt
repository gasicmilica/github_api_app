package com.example.github_api_app.data.mapper

import com.example.github_api_app.data.model.RepoDetailsResponse
import com.example.github_api_app.data.model.RepoDetailsUi
import com.example.github_api_app.data.model.UserRepoResponse
import com.example.github_api_app.data.model.UserRepoUi

fun UserRepoResponse.toUserRepoUi(): UserRepoUi {
    return UserRepoUi(
        repoName = this.name,
        openIssueCount = this.openIssueCount
    )
}

fun RepoDetailsResponse.toRepoDetailsUi(): RepoDetailsUi {
    return RepoDetailsUi(
        name = this.name,
        watchersCount = this.watchersCount,
        forksCount = this.forksCount
    )
}