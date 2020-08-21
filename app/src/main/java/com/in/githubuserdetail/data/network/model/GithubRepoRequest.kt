package com.`in`.githubuserdetail.data.network.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class GithubRepoRequest(
    val username: String,
    val hasInternet: Boolean
)