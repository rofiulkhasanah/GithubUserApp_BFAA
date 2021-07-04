package com.dicoding.picodiploma.githubuserapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersFav(
    val id_user: Int,
    val login: String,
    val avatar_url: String
) : Parcelable