package com.`in`.githubuserdetail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.`in`.githubuserdetail.R
import com.`in`.githubuserdetail.data.network.model.GithubUserRepo
import com.`in`.githubuserdetail.ui.userrepository.UserRepoConstant
import kotlinx.android.synthetic.main.repo_list_item.view.*

class GithubUserRepoAdapter : PagedListAdapter<GithubUserRepo, GithubUserRepoAdapter.RepoViewHolder>(USER_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_list_item, parent, false)
        return RepoViewHolder(view)
    }
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.let { holder.bind(it) }
    }
    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvRepoName = view.tvreponame
        private val tvRepoDesc = view.tvrepodesc
        private val tvRepoLang = view.tvrepolang
        private val tvRepoWatchers = view.tvrepowatchers
        fun bind(repo: GithubUserRepo) {
            tvRepoName.setText(repo.name)
            tvRepoDesc.setText(if(!repo.description.isNullOrEmpty())repo.description else UserRepoConstant.DESCRIPTION)
            tvRepoLang.setText(if(!repo.language.isNullOrEmpty())repo.language else UserRepoConstant.LANGUAGE)
            tvRepoWatchers.setText("${repo.watchers} ${UserRepoConstant.WATCHERS}")
        }
    }
    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<GithubUserRepo>() {
            override fun areItemsTheSame(oldItem: GithubUserRepo, newItem: GithubUserRepo): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: GithubUserRepo, newItem: GithubUserRepo): Boolean =
                newItem == oldItem
        }
    }
}