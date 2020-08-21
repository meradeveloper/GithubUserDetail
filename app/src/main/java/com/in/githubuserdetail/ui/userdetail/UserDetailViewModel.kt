package com.`in`.githubuserdetail.ui.userdetail

import androidx.lifecycle.ViewModel
import com.`in`.githubuserdetail.data.Repository

class UserDetailViewModel(private val repository: Repository):ViewModel() {

    fun getUser() = repository.getUser()
}