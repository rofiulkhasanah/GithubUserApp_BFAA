package com.dicoding.picodiploma.githubuserapp.data.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavUserDao {

    @Query("SELECT * FROM tFavoriteUser")
    fun getAllFavUser(): LiveData<List<FavUserGithub>>

    @Query("SELECT count(*) FROM tFavoriteUser WHERE id_user == :userId")
    fun checkUser(userId: Int): Int

    @Query("DELETE FROM tfavoriteuser WHERE id_user == :userId")
    fun deleteUserFav(userId: Int): Int

    @Insert
    fun insertFav(favUser: FavUserGithub)

    @Query("SELECT * FROM tFavoriteUser")
    fun findAllFavUser(): Cursor


}