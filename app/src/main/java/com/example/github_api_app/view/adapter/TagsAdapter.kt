package com.example.github_api_app.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github_api_app.data.model.TagUi
import com.example.github_api_app.databinding.TagItemBinding

class TagsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tags = arrayListOf<TagUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(TagItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(tags[position])
    }

    fun setItems(items: List<TagUi>) {
        tags.clear()
        tags.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: TagItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repoItem: TagUi) = with(binding) {
            tvTagName.text = repoItem.name
            tvShaValue.text = repoItem.sha
        }
    }
}