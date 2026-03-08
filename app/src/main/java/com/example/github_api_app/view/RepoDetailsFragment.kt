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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.github_api_app.Constants
import com.example.github_api_app.R
import com.example.github_api_app.data.model.State
import com.example.github_api_app.databinding.RepoDetailsFragmentBinding
import com.example.github_api_app.view_model.RepoDetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoDetailsFragment : Fragment() {

    private var _repoDetailsFragmentBinding: RepoDetailsFragmentBinding? = null
    private val binding get() = _repoDetailsFragmentBinding!!

    private val repoDetailsViewModel: RepoDetailsViewModel by viewModel()
    private val args: RepoDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _repoDetailsFragmentBinding = RepoDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                repoDetailsViewModel.userInfoState.collect { state ->
                    when(state) {
                        is State.Loading -> {
                            binding.loader.isVisible = true
                        }
                        is State.Success -> {
                            binding.tvOwnerName.text = state.data.name
                            Glide.with(binding.root)
                                .load(state.data.avatarUrl)
                                .error(R.drawable.icon_arrow_forward)
                                .circleCrop()
                                .into(binding.ivOwnerAvatar)
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
                repoDetailsViewModel.state.collect { state ->
                    when(state) {
                        is State.Loading -> {
                            binding.loader.isVisible = true
                        }
                        is State.Success -> {
                            binding.tvRepoName.text = state.data.name
                            binding.tvWatchersCount.text = state.data.watchersCount.toString()
                            binding.tvForksCount.text = state.data.forksCount.toString()

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

        repoDetailsViewModel.loadUserInfo(Constants.TEST_USER_NAME)
        repoDetailsViewModel.loadRepoDetails(Constants.TEST_USER_NAME, args.repoName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _repoDetailsFragmentBinding = null
    }

}