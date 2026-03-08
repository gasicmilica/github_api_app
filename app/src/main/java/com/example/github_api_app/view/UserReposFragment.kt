package com.example.github_api_app.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github_api_app.Constants
import com.example.github_api_app.R
import com.example.github_api_app.databinding.UserReposFragmentBinding
import com.example.github_api_app.data.model.State
import com.example.github_api_app.data.model.UserRepoUi
import com.example.github_api_app.view.adapter.ReposAdapter
import com.example.github_api_app.view_model.UserReposViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserReposFragment : Fragment() {

    private var _userReposFragmentBinding: UserReposFragmentBinding? = null
    private val binding get() = _userReposFragmentBinding!!

    private val userReposViewModel: UserReposViewModel by viewModel()
    private lateinit var reposAdapter: ReposAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _userReposFragmentBinding = UserReposFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reposAdapter = ReposAdapter(object: ReposAdapter.RepoItemListener {
            override fun onItemClicked(repo: UserRepoUi) {
                findNavController().navigate(
                    UserReposFragmentDirections.actionUserReposFragmentToRepoDetailsFragment(repo.repoName)
                )
            }
        })

        binding.rvRepos.adapter = reposAdapter
        binding.rvRepos.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userReposViewModel.userState.collect { state ->
                    when(state) {
                        is State.Loading -> {
                            binding.loader.isVisible = true
                        }
                        is State.Success -> {
                            binding.tvUserName.text = state.data.name
                            binding.loader.isVisible = false
                        }
                        is State.Error -> {
                            Log.d("Error", state.message)
                            binding.loader.isVisible = false
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userReposViewModel.userReposState.collect { state ->
                    when(state) {
                        is State.Loading -> {
                            binding.loader.isVisible = true
                        }
                        is State.Success -> {
                            binding.noDataView.root.isVisible = state.data.isEmpty()
                            binding.noDataView.tvNoContent.text = getString(R.string.no_repos_found)
                            binding.rvRepos.isVisible = state.data.isNotEmpty()
                            reposAdapter.setItems(state.data)
                            binding.loader.isVisible = false
                        }
                        is State.Error -> {
                            Log.d("Error", state.message)
                            binding.loader.isVisible = false
                        }
                    }
                }
            }
        }

        userReposViewModel.loadUserInfo(Constants.TEST_USER_NAME)
        userReposViewModel.loadUserRepos(Constants.TEST_USER_NAME)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _userReposFragmentBinding = null
    }

}