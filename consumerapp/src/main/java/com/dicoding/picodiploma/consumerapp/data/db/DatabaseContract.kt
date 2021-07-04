package com.dicoding.picodiploma.consumerapp.data.db

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.dicoding.picodiploma.githubuserapp"
    const val SCHEME = "content"

    internal class UserColumn : BaseColumns {
        companion object {
            private const val TABLE_NAME = "tFavoriteUser"
            const val ID_USER = "id_user"
            const val NAME = "login"
            const val AVA_URL = "avatar_url"

            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}