package com.dicoding.picodiploma.githubuserapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tFavoriteUser")
data class FavUserGithub(
    @PrimaryKey val id_user: Int,
    @ColumnInfo val login: String,
    @ColumnInfo val avatar_url: String
) : Serializable