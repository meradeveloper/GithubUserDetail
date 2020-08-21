package com.`in`.githubuserdetail.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    val NO_INTERNET = "No Internet Connection Available!"
    val NETWORK_ERROR = "Slow Network or Something Error!"

    fun isInternetAvailable(context:Context):Boolean
    {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null
    }

}