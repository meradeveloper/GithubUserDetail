package com.`in`.githubuserdetail.data.network

import com.`in`.githubuserdetail.data.network.model.GithubUser
import com.`in`.githubuserdetail.data.network.model.GithubUserRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("/users/{user}")
    fun fetchGithubUser(@Path("user") user:String): Call<GithubUser>

    @GET("/users/{username}/repos")
    fun fetchGithubUserRepos(@Path("username") username:String,
                             @Query("page") page: Int): Call<List<GithubUserRepo>>
}