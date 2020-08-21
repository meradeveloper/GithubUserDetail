package com.`in`.githubuserdetail.ui.userrepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.data.network.model.GithubRepoRequest
import com.`in`.githubuserdetail.data.network.model.GithubRepoResult
import com.`in`.githubuserdetail.data.network.model.GithubUserRepo

class GithubUserRepoViewModel(private val repository: Repository):ViewModel() {

    private val usernamedata = MutableLiveData<GithubRepoRequest>()

    private val githubRepoResult  : LiveData<GithubRepoResult> = Transformations.map(usernamedata){
        repository.getUserRepositories(it)
    }

    val githubRepo : LiveData<PagedList<GithubUserRepo>> = Transformations.switchMap(githubRepoResult){
        it.data
    }

    val githubRepoError  : LiveData<String> = Transformations.switchMap(githubRepoResult){
        it.networkErrors
    }

    fun getRepositories(repoReq:GithubRepoRequest)
    {
        usernamedata.postValue(repoReq)
    }
}