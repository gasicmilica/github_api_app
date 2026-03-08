package com.example.github_api_app.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_api_app.data.mapper.toRepoDetailsUi
import com.example.github_api_app.data.mapper.toTagUi
import com.example.github_api_app.data.mapper.toUserUi
import com.example.github_api_app.data.model.RepoDetailsUi
import com.example.github_api_app.data.model.State
import com.example.github_api_app.data.model.TagUi
import com.example.github_api_app.data.model.UserUi
import com.example.github_api_app.data.repository.GithubRepoRepository
import com.example.github_api_app.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoDetailsViewModel(
    private val githubRepoRepository: GithubRepoRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<State<RepoDetailsUi>>(State.Loading)
    val state: StateFlow<State<RepoDetailsUi>> = _state.asStateFlow()

    fun loadRepoDetails(userName: String, repoName: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            val result = githubRepoRepository.getRepoDetails(userName, repoName)
            _state.value = when (result) {
                is State.Success -> State.Success(result.data.toRepoDetailsUi())
                is State.Error -> result
                is State.Loading -> State.Loading
            }
        }
    }

    private val _userInfoState = MutableStateFlow<State<UserUi>>(State.Loading)
    val userInfoState: StateFlow<State<UserUi>> = _userInfoState.asStateFlow()

    fun loadUserInfo(userName: String) {
        viewModelScope.launch {
            _userInfoState.value = State.Loading
            val result = userRepository.getUserInfo(userName)
            _userInfoState.value = when (result) {
                is State.Success -> State.Success(result.data.toUserUi())
                is State.Error -> result
                is State.Loading -> State.Loading
            }
        }
    }

    private val _tagsState = MutableStateFlow<State<List<TagUi>>>(State.Loading)
    val tagsState: StateFlow<State<List<TagUi>>> = _tagsState.asStateFlow()

    fun loadRepoTags(userName: String, repoName: String) {
        viewModelScope.launch {
            _tagsState.value = State.Loading
            val result = githubRepoRepository.getRepoTags(userName, repoName)
            _tagsState.value = when (result) {
                is State.Success -> State.Success(result.data.map { it.toTagUi() })
                is State.Error -> result
                is State.Loading -> State.Loading
            }
        }
    }

}