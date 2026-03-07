package com.example.github_api_app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_api_app.data.model.State
import com.example.github_api_app.data.model.User
import com.example.github_api_app.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserReposViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userState = MutableLiveData<State<User>>()
    val userState: LiveData<State<User>> = _userState

    fun loadUserInfo(userName: String) {
        viewModelScope.launch {
            _userState.value = State.Loading

            try {
                val user = userRepository.getUserInfo(userName)
                _userState.value = State.Success(user)
            } catch (e: Exception) {
                _userState.value = State.Error(e.message ?: "Unknown error")
            }
        }
    }

}