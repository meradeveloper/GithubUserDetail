package com.`in`.githubuserdetail.ui.userdetail

import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.data.cache.LocalCache
import com.`in`.githubuserdetail.data.network.GithubService
import com.`in`.githubuserdetail.ui.enteruser.UserViewModel
import dagger.Module
import dagger.Provides

@Module
class UserDetailModule {

    @Provides
    fun provideUserDetailViewModel(repository: Repository) = UserDetailViewModel(repository)
}