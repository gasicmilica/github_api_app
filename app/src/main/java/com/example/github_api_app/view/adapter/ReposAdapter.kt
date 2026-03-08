package com.example.github_api_app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github_api_app.databinding.RepoItemBinding
import com.example.github_api_app.model.UserRepoUi

class ReposAdapter(private val listener: RepoItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repos = arrayListOf<UserRepoUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(repos[position])
    }

    fun setItems(items: List<UserRepoUi>) {
        repos.clear()
        repos.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repoItem: UserRepoUi) = with(binding) {
            root.setOnClickListener { listener.onItemClicked(repoItem) }
            tvRepoName.text = repoItem.repoName
            tvOpenIssuesCount.text = repoItem.openIssueCount.toString()
        }
    }

    interface RepoItemListener {
        fun onItemClicked(repo: UserRepoUi)
    }
}