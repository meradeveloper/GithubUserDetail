package com.`in`.githubuserdetail.data.network.model

import androidx.lifecycle.LiveData

data class UserResult(
    val data: LiveData<GithubUser>,
    val networkErrors: LiveData<String>
)