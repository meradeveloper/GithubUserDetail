package com.`in`.githubuserdetail.di

import android.app.Application
import android.content.Context
import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.data.cache.GithubUserDB
import com.`in`.githubuserdetail.data.cache.LocalCache
import com.`in`.githubuserdetail.data.network.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideRetrofit(retrofitbuilder: Retrofit.Builder): Retrofit {
        retrofitbuilder.baseUrl("https://api.github.com")
        return retrofitbuilder.build()
    }

    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService = retrofit.create(GithubService::class.java)


    @Singleton
    @Provides
    fun provideDatabase(context: Application) = GithubUserDB.getInstance(context)
}