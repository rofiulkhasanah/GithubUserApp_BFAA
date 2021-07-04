package com.dicoding.picodiploma.githubuserapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavUserGithub::class), version = 1)
abstract class GithubUserRoomDB : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: GithubUserRoomDB? = null

        fun getDB(context: Context): GithubUserRoomDB {
            return INSTANCE ?: synchronized(this) {
                val insDB = Room.databaseBuilder(
                    context.applicationContext,
                    GithubUserRoomDB::class.java,
                    "GithubUser_Fav"
                ).build()
                INSTANCE = insDB
                insDB
            }
        }
    }

    abstract fun githubUserFavDao(): FavUserDao
}
