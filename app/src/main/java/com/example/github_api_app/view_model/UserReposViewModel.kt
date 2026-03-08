package com.example.github_api_app.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_api_app.data.mapper.toUserRepoUi
import com.example.github_api_app.data.mapper.toUserUi
import com.example.github_api_app.data.response.State
import com.example.github_api_app.data.repository.GithubRepoRepository
import com.example.github_api_app.data.repository.UserRepository
import com.example.github_api_app.model.UserRepoUi
import com.example.github_api_app.model.UserUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserReposViewModel(
    private val userRepository: UserRepository,
    private val githubRepoRepository: GithubRepoRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<State<UserUi>>(State.Loading)
    val userState: StateFlow<State<UserUi>> = _userState.asStateFlow()

    fun loadUserInfo(userName: String) {
        viewModelScope.launch {
            _userState.value = State.Loading
            val result = userRepository.getUserInfo(userName)
            _userState.value = when (result) {
                is State.Success -> State.Success(result.data.toUserUi()) 
                is State.Error -> result
                is State.Loading -> State.Loading
            }
        }
    }

    private val _userReposState = MutableStateFlow<State<List<UserRepoUi>>>(State.Loading)
    val userReposState: StateFlow<State<List<UserRepoUi>>> = _userReposState.asStateFlow()

    fun loadUserRepos(userName: String) {
        viewModelScope.launch {
            _userReposState.value = State.Loading
            val result = githubRepoRepository.getUserRepos(userName)
            _userReposState.value = when (result) {
                is State.Success -> State.Success(result.data.map { it.toUserRepoUi() })
                is State.Error -> result
                is State.Loading -> State.Loading
            }
        }
    }

}