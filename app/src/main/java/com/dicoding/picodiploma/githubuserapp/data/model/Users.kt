package com.dicoding.picodiploma.githubuserapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Users(
    val items: ArrayList<User>
)

@Parcelize
data class User(
    val login: String,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val repos_url: String,
    val company: String,
    val location: String,
    val name: String,
    val following: Int,
    val followers: Int,
    val public_repos: Int,
    val id: Int
) : Parcelable
