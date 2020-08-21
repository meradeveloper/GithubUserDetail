package com.`in`.githubuserdetail.data.network.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class GithubRepoResult(
    val data: LiveData<PagedList<GithubUserRepo>>,
    val networkErrors: LiveData<String>
)