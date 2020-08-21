package com.`in`.githubuserdetail.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder
    {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
    }
}

