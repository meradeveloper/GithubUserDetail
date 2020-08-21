package com.`in`.githubuserdetail.data.cache.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.`in`.githubuserdetail.data.network.model.GithubUserRepo

@Dao
interface GithubUserRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<GithubUserRepo>)

    @Query("DELETE FROM githubuserrepo")
    fun deleteRepo()

    // return photos
    @Query("SELECT * FROM githubuserrepo")
    fun getGithubUserRepo(): DataSource.Factory<Int, GithubUserRepo>
}