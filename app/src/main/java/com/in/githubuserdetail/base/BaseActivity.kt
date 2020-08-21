package com.`in`.githubuserdetail.base

import android.app.ProgressDialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import android.widget.Toast
import com.`in`.githubuserdetail.utils.NetworkUtils
import com.`in`.githubuserdetail.R

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mProgress: ProgressDialog

    abstract fun onViewCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        onViewCreated()
    }

    private fun initialize() {
        AndroidInjection.inject(this)
        mProgress = ProgressDialog(this)
    }

    fun checkInternet() = NetworkUtils.isInternetAvailable(this)

    fun showMessage(message:String) {
        Toast.makeText(this, "\uD83D\uDE28 Wooops $message", Toast.LENGTH_LONG).show()}

    fun setLoading()
    {
        mProgress.setMessage(getString(R.string.progress))
        mProgress.setCancelable(false)
        mProgress.show()
    }

    fun hideLoding()= mProgress.dismiss()

}
