package com.`in`.githubuserdetail.ui.enteruser

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.`in`.githubuserdetail.utils.NetworkUtils
import com.`in`.githubuserdetail.R
import com.`in`.githubuserdetail.ViewModelFactory
import com.`in`.githubuserdetail.base.BaseActivity
import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.ui.userdetail.UserDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class EnterUserActivity : BaseActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel

    @Inject
    lateinit var Repository:Repository

    override fun onViewCreated() {
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this, ViewModelFactory(userViewModel))
            .get(UserViewModel::class.java)
        btn_search.setOnClickListener {
            if(checkInternet())
            {
                setLoading()
                userViewModel.findUser(etUsername.text.toString().trim())
            }
            else
                showMessage(NetworkUtils.NO_INTERNET)
        }
        setLiveDataObservers()
    }

    private fun setLiveDataObservers() {
        userViewModel.user.observe(this, Observer{
            hideLoding()
            startActivity(Intent(this,UserDetailActivity::class.java))
        })

        userViewModel.networkErrors.observe(this, Observer {
            hideLoding()
            showMessage(it)
        })
    }

}
