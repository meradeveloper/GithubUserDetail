package com.`in`.githubuserdetail.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.`in`.githubuserdetail.utils.NetworkUtils
import com.`in`.githubuserdetail.data.cache.BoundaryCondition
import com.`in`.githubuserdetail.data.cache.LocalCache
import com.`in`.githubuserdetail.data.network.GithubService
import com.`in`.githubuserdetail.data.network.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(val service: GithubService
                                          , val cache: LocalCache)
{

    private val _networkErrors = MutableLiveData<String>()
    private val _userdata = MutableLiveData<GithubUser>()

    fun getUser() = cache.getUser()

    fun searchUser(user: String): UserResult {

        cleanCache()
        service.fetchGithubUser(user).enqueue(
            object : Callback<GithubUser> {
                override fun onFailure(call: Call<GithubUser>?, t: Throwable) {
                    _networkErrors.postValue(t.message ?: NetworkUtils.NETWORK_ERROR)
                }

                override fun onResponse(
                    call: Call<GithubUser>?,
                    response: Response<GithubUser>
                ) {
                    if (response.isSuccessful) {
                        val user = response.body()!!
                        cache.insertUser(user) {
                            _userdata.postValue(user)
                        }
                    } else {
                        _networkErrors.postValue(response.message() ?: NetworkUtils.NETWORK_ERROR)
                    }
                }
            }
        )

        return UserResult(_userdata, _networkErrors)
    }

    private fun cleanCache() {
        cache.deleteUser()
    }

    fun cleanRepos()
    {
        cache.deleteRepo()
    }

    fun getUserRepositories(repoReq: GithubRepoRequest): GithubRepoResult {

        // Get data source factory from the local cache
        val dataSourceFactory = cache.getRepositories()

        // Construct the boundary callback
        val boundaryCallback = BoundaryCondition(repoReq, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return GithubRepoResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}