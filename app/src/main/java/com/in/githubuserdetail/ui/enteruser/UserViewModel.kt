package com.`in`.githubuserdetail.ui.enteruser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.data.network.model.GithubUser
import com.`in`.githubuserdetail.data.network.model.UserResult
import javax.inject.Inject

class UserViewModel(private val repository: Repository): ViewModel() {

    private var userNameLiveData= MutableLiveData<String>();

     var userlivedataresult: LiveData<UserResult> = Transformations.map(userNameLiveData) {
        repository.searchUser(it)
    }

    var networkErrors: LiveData<String> = Transformations.switchMap(userlivedataresult) { it ->
        it.networkErrors
    }

    val user: LiveData<GithubUser> = Transformations.switchMap(userlivedataresult) { it -> it.data }

    fun findUser(username:String)
    {
        userNameLiveData.postValue(username)
    }
}