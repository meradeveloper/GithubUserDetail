package com.`in`.githubuserdetail.ui.enteruser

import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.data.cache.GithubUserDB
import com.`in`.githubuserdetail.data.cache.LocalCache
import com.`in`.githubuserdetail.data.network.GithubService
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun provideUserViewModel(repository: Repository) = UserViewModel(repository)

}