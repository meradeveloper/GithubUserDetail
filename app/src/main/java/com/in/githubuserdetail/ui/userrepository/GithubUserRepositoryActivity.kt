package com.`in`.githubuserdetail.ui.userrepository

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.`in`.githubuserdetail.utils.NetworkUtils
import com.`in`.githubuserdetail.R
import com.`in`.githubuserdetail.ViewModelFactory
import com.`in`.githubuserdetail.base.BaseActivity
import com.`in`.githubuserdetail.data.network.model.GithubRepoRequest
import com.`in`.githubuserdetail.ui.GithubUserRepoAdapter
import com.`in`.githubuserdetail.ui.userdetail.UserDetailActivity
import kotlinx.android.synthetic.main.activity_user_repository.*
import javax.inject.Inject

class GithubUserRepositoryActivity : BaseActivity() {

    @Inject
    lateinit var userRepoViewModel: GithubUserRepoViewModel
    @Inject
    lateinit var repoAdapter: GithubUserRepoAdapter

    override fun onViewCreated() {
        setContentView(R.layout.activity_user_repository)
        val username = intent.extras?.getString(UserDetailActivity.KEY)
        userRepoViewModel = ViewModelProviders.of(this, ViewModelFactory(userRepoViewModel))
            .get(GithubUserRepoViewModel::class.java)
        if (username!=null)
        {
            setLoading()
            userRepoViewModel.getRepositories(
                GithubRepoRequest(username,checkInternet()))
        }

        setAdapter()
        setPagedLiveData()
    }

    private fun setPagedLiveData() {
        userRepoViewModel.githubRepo.observe(this, Observer {
            // showing, when first time loaded from server and no data found in cache
            setLoadingRepoText(it.size==0)
            repoAdapter.submitList(it)
            hideLoding()
        })

        userRepoViewModel.githubRepoError.observe(this, Observer {
            hideLoding()
            showMessage(NetworkUtils.NETWORK_ERROR)
        })
    }

    private fun setAdapter() {
        recyclerView.adapter = repoAdapter
        recyclerView.setHasFixedSize(true);
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setLoadingRepoText(show:Boolean)
    {
        if(show)
        {
            loadingList.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
        else
        {
            loadingList.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

}
