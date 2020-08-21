package com.`in`.githubuserdetail.data.cache

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.`in`.githubuserdetail.data.network.model.GithubUser
import com.`in`.githubuserdetail.data.network.model.GithubUserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

open class LocalCache @Inject constructor(
     val githubUserDB: GithubUserDB
) {

    // Insert User in the database, on a background thread.
    fun insertUser(user: GithubUser, insertFinished: () -> Unit) {
        // Scope for background processing
        CoroutineScope(Dispatchers.IO).launch {
            githubUserDB.githubUserDao().insert(user)
            insertFinished()
        }
    }

    // Insert latest photos in the database, on a background thread.
    fun insertUserRepo(repos: List<GithubUserRepo>, insertFinished: () -> Unit) {
        // Scope for background processing
        CoroutineScope(Dispatchers.IO).launch {
            githubUserDB.githubUserRepoDao().insert(repos)
            insertFinished()
        }
    }

    // Request a LiveData<List<Photo>> from the Dao, based on a newly inserted photo.
    fun getUser():LiveData<GithubUser>{
        return githubUserDB.githubUserDao().getUser()
    }
    fun getUserByUsername(username:String): GithubUser = githubUserDB.githubUserDao().getUserByUsername(username)

    // Request a LiveData<List<UserGithubRepo>> from the Dao, based on a newly inserted Repository.
    fun getRepositories(): DataSource.Factory<Int, GithubUserRepo> = githubUserDB.githubUserRepoDao().getGithubUserRepo()

    fun deleteUser()
    {
        CoroutineScope(Dispatchers.IO).launch {
            githubUserDB.githubUserDao().delete()
        }
    }

    fun deleteRepo()
    {
        CoroutineScope(Dispatchers.IO).launch {
            githubUserDB.githubUserRepoDao().deleteRepo()
        }
    }
}