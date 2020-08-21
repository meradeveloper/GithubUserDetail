package com.`in`.githubuserdetail.data.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.`in`.githubuserdetail.utils.NetworkUtils
import com.`in`.githubuserdetail.data.network.GithubService
import com.`in`.githubuserdetail.data.network.model.GithubRepoRequest
import com.`in`.githubuserdetail.data.network.model.GithubUserRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoundaryCondition(
    private val repoRequest: GithubRepoRequest,
    private val service: GithubService,
    private val cache: LocalCache
) : PagedList.BoundaryCallback<GithubUserRepo>() {

    init {
        if(repoRequest.hasInternet)
            cache.deleteRepo()
    }

    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestRepoFromServer(repoRequest.username)
    }

    override fun onItemAtEndLoaded(itemAtEnd: GithubUserRepo) {
        requestRepoFromServer(repoRequest.username)
    }

    private fun requestRepoFromServer(username: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        service.fetchGithubUserRepos(username, lastRequestedPage).enqueue(
            object : Callback<List<GithubUserRepo>> {
                override fun onFailure(call: Call<List<GithubUserRepo>>?, t: Throwable) {
                    _networkErrors.postValue(t.message ?: NetworkUtils.NETWORK_ERROR)
                }

                override fun onResponse(
                    call: Call<List<GithubUserRepo>>?,
                    response: Response<List<GithubUserRepo>>
                ) {
                    if (response.isSuccessful) {
                        val repos = response.body()!!
                        cache.insertUserRepo(repos) {
                            lastRequestedPage++
                            isRequestInProgress = false
                        }
                    } else {
                        isRequestInProgress = false
                        _networkErrors.postValue(response.errorBody()?.string() ?: NetworkUtils.NETWORK_ERROR)
                    }
                }
            }
        )
    }
}

