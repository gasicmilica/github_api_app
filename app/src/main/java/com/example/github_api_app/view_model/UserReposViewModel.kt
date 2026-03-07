package com.example.github_api_app.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_api_app.data.model.State
import com.example.github_api_app.data.model.User
import com.example.github_api_app.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserReposViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow<State<User>>(State.Loading)
    val userState: StateFlow<State<User>> = _userState.asStateFlow()

    fun loadUserInfo(userName: String) {
        viewModelScope.launch {
            _userState.value = State.Loading
            val result = userRepository.getUserInfo(userName)
            _userState.value = result
        }
    }

}