package com.`in`.githubuserdetail.ui.userrepository

import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.data.cache.LocalCache
import com.`in`.githubuserdetail.data.network.GithubService
import com.`in`.githubuserdetail.ui.GithubUserRepoAdapter
import dagger.Module
import dagger.Provides

@Module
class GithubUserRepoModule {

    @Provides
    fun provideUserDetailViewModel(repository: Repository) = GithubUserRepoViewModel(repository)

    @Provides
    fun provideRepoAdapter()=GithubUserRepoAdapter()
}