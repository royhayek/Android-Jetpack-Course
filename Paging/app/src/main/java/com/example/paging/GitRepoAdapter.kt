package com.example.paging

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class GitRepoAdapter: PagingDataAdapter<GitRepoAdapter.RepoViewHolder>(REPO_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class RepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean =
               oldItem.fullName == newItem.fullName


            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean =
                oldItem == newItem

        }
    }

}