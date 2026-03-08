package com.example.github_api_app.data.mapper

import com.example.github_api_app.data.response.RepoDetailsResponse
import com.example.github_api_app.model.RepoDetailsUi
import com.example.github_api_app.data.response.TagResponse
import com.example.github_api_app.data.response.UserRepoResponse
import com.example.github_api_app.model.TagUi
import com.example.github_api_app.model.UserRepoUi

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

fun TagResponse.toTagUi(): TagUi {
    return TagUi(
        name = this.name,
        sha = this.commit.sha
    )
}