package com.dicoding.picodiploma.consumerapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersFav(
    val id: Int,
    val login: String,
    val avatar_url: String
) : Parcelable