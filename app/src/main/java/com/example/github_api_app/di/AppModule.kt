package com.example.github_api_app.di

import com.example.github_api_app.data.api.ApiService
import com.example.github_api_app.data.repository.UserRepository
import com.example.github_api_app.view_model.UserReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { UserReposViewModel(get()) }
}

val repositoryModule = module {
    single { UserRepository(get()) }
}

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
}