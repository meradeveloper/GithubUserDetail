package com.`in`.githubuserdetail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory<V : Any>(val viewmodel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewmodel::class.java)) {
                return viewmodel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

