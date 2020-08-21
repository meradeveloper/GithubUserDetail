package com.`in`.githubuserdetail.ui.userdetail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.`in`.githubuserdetail.R
import com.`in`.githubuserdetail.ViewModelFactory
import com.`in`.githubuserdetail.base.BaseActivity
import com.`in`.githubuserdetail.data.network.model.GithubUser
import com.`in`.githubuserdetail.ui.userrepository.GithubUserRepositoryActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {

    @Inject
    lateinit var userDetailViewModel: UserDetailViewModel
    lateinit var githubUser: GithubUser
    lateinit var requestOptions: RequestOptions
    override fun onViewCreated() {
        setContentView(R.layout.activity_user_detail)
        userDetailViewModel = ViewModelProviders.of(this, ViewModelFactory(userDetailViewModel))
            .get(UserDetailViewModel::class.java)

        userDetailViewModel.getUser().observe(this, Observer {
            githubUser = it
            if (githubUser!=null)
                setUserDetail()
            else
                showMessage(resources.getString(R.string.no_user_available))
        })
    }

    private fun setUserDetail() {

        setAvator()
        tvname.setText(if(!githubUser.name.isNullOrEmpty()) githubUser.name else UserDetailConstants.Name)
        tvusername.setText(if(!githubUser.login.isNullOrEmpty()) githubUser.login else UserDetailConstants.UserName)
        tvfollower.setText("${githubUser.followers} ${UserDetailConstants.Followers}")
        tvfollowing.setText("${githubUser.following} ${UserDetailConstants.Following}")
        tvlocation.setText(if(!githubUser.location.isNullOrEmpty()) githubUser.location else UserDetailConstants.Location)
        tvbio.setText(if(!githubUser.bio.isNullOrEmpty()) githubUser.bio else UserDetailConstants.Bio)
        tvCompany.setText(if(!githubUser.company.isNullOrEmpty()) githubUser.company else UserDetailConstants.Company)
        tvblog.setText(if(!githubUser.blog.isNullOrEmpty()) githubUser.blog else UserDetailConstants.Blog)
        btn_repo.setText(if(githubUser.public_repos!=null) "${githubUser.public_repos} ${UserDetailConstants.Repositories}" else UserDetailConstants.Repositories)

        btn_repo.setOnClickListener {
            githubUser.public_repos?.let {
                if(it>0)
                    goToRepository()
                else
                    showMessage(resources.getString(R.string.no_repo))
            }
        }
    }

    private fun goToRepository() {
        startActivity(Intent(this,GithubUserRepositoryActivity::class.java).putExtra(KEY,githubUser.login))
    }

    private fun setAvator() {
        requestOptions = RequestOptions()
        Glide.with(this)
            .load(githubUser.avatar_url)
            .apply(requestOptions.circleCrop())
            .placeholder(ColorDrawable(Color.LTGRAY))
            .into(iv_avator);
    }

    companion object{ val KEY = "USERNAME"}
}
