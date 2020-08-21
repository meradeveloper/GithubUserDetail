package com.`in`.githubuserdetail.di

import android.app.Application
import com.`in`.githubuserdetail.GithubUserDetailApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, ActivityBuilder::class))
interface AppComponent {
    fun inject(githubUserDetailApp: GithubUserDetailApp)

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent

    }
}