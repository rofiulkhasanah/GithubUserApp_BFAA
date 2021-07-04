package com.dicoding.picodiploma.githubuserapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicoding.picodiploma.githubuserapp.data.db.FavUserDao
import com.dicoding.picodiploma.githubuserapp.data.db.GithubUserRoomDB

class CustomerProvider : ContentProvider() {

    companion object {
        //        private const val FAVORIT = 1
        private const val FAVORIT_ID = 1
        private const val AUTHORITY = "com.dicoding.picodiploma.githubuserapp"
        private const val TABLE_NAME = "tFavoriteUser"
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORIT_ID)
        }
    }

    private lateinit var favUserDao: FavUserDao


    override fun onCreate(): Boolean {
        favUserDao = context?.let { GithubUserRoomDB.getDB(it).githubUserFavDao() }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when (sUriMatcher.match(uri)) {
            FAVORIT_ID -> {
                cursor = favUserDao.findAllFavUser()
                if (context != null) {
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                cursor = null
            }
        }
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}