package com.`in`.githubuserdetail.userinput

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.`in`.githubuserdetail.data.Repository
import com.`in`.githubuserdetail.data.network.model.GithubUser
import com.`in`.githubuserdetail.data.network.model.UserResult
import com.`in`.githubuserdetail.ui.enteruser.UserViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class UserViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    /*@Mock
    lateinit var repositoryHelper: RepositoryHelper*/

    @Mock
    lateinit var repository: Repository

    @Mock
    lateinit var observer: Observer<UserResult>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        var userViewModel = UserViewModel(repository)
        userViewModel.userlivedataresult.observeForever(observer)
    }

    @Test
    fun testUserApiFetchDataSuccess() { // Mock API response

        var data = MutableLiveData<GithubUser>()
        var error = MutableLiveData<String>()

        //verify(repositoryHelper).getUsers("meradeveloper")
        //verify(observer).onChanged(UserResult(data,error))

    }
}