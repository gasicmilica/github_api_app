package com.example.github_api_app.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.github_api_app.Constants
import com.example.github_api_app.databinding.UserReposFragmentBinding
import com.example.github_api_app.data.model.State
import com.example.github_api_app.view_model.UserReposViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserReposFragment : Fragment() {

    private var _userReposFragmentBinding: UserReposFragmentBinding? = null
    private val binding get() = _userReposFragmentBinding!!

    private val userReposViewModel: UserReposViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _userReposFragmentBinding = UserReposFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userReposViewModel.userState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is State.Loading -> {
                    binding.loader.isVisible = true
                }
                is State.Success -> {
                    binding.tvUserName.text = state.data?.name
                    binding.loader.isVisible = false
                }
                is State.Error -> {
                    Log.d("Error", state.message)
                    binding.loader.isVisible = false
                }
            }
        }

        userReposViewModel.loadUserInfo(Constants.TEST_USER_NAME)
    }

}