package com.example.github_api_app.di

import com.example.github_api_app.data.repository.GithubRepoRepository
import com.example.github_api_app.data.repository.UserRepository
import com.example.github_api_app.view_model.RepoDetailsViewModel
import com.example.github_api_app.view_model.UserReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserReposViewModel(get(), get()) }
    viewModel { RepoDetailsViewModel(get(), get()) }
}

val repositoryModule = module {
    single { UserRepository(get()) }
    single { GithubRepoRepository(get()) }
}
