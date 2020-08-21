package com.`in`.githubuserdetail.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.`in`.githubuserdetail.data.cache.dao.GithubUserDao
import com.`in`.githubuserdetail.data.cache.dao.GithubUserRepoDao
import com.`in`.githubuserdetail.data.network.model.GithubUser
import com.`in`.githubuserdetail.data.network.model.GithubUserRepo

@Database(
    entities = arrayOf(GithubUser::class,GithubUserRepo::class),
    version = 1,
    exportSchema = false
)
abstract class GithubUserDB : RoomDatabase() {

    abstract fun githubUserDao(): GithubUserDao
    abstract fun githubUserRepoDao(): GithubUserRepoDao

    companion object {

        @Volatile
        private var INSTANCE: GithubUserDB? = null

        fun getInstance(context: Context): GithubUserDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                GithubUserDB::class.java, "githubuser.db")
                .build()
    }
}