package com.`in`.githubuserdetail.data.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.`in`.githubuserdetail.data.network.model.GithubUser

@Dao
interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUser)

    @Query("DELETE FROM githubuser")
    fun delete()

    // return user
    @Query("SELECT * FROM githubuser WHERE login = :username")
    fun getUserByUsername(username:String): GithubUser

    // return user
    @Query("SELECT * FROM githubuser")
    fun getUser(): LiveData<GithubUser>

}