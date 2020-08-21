package com.`in`.githubuserdetail.di

import com.`in`.githubuserdetail.ui.enteruser.EnterUserActivity
import com.`in`.githubuserdetail.ui.enteruser.UserModule
import com.`in`.githubuserdetail.ui.userdetail.UserDetailActivity
import com.`in`.githubuserdetail.ui.userdetail.UserDetailModule
import com.`in`.githubuserdetail.ui.userrepository.GithubUserRepoModule
import com.`in`.githubuserdetail.ui.userrepository.GithubUserRepositoryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(UserModule::class))
    abstract fun UserActivityInjector(): EnterUserActivity

    @ContributesAndroidInjector(modules = arrayOf(UserDetailModule::class))
    abstract fun UserDetailActivityInjector(): UserDetailActivity

    @ContributesAndroidInjector(modules = arrayOf(GithubUserRepoModule::class))
    abstract fun UserRepositoryActivityInjector(): GithubUserRepositoryActivity

}