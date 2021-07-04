package com.dicoding.picodiploma.consumerapp.helper

import android.database.Cursor
import com.dicoding.picodiploma.consumerapp.data.db.DatabaseContract
import com.dicoding.picodiploma.consumerapp.model.UsersFav

object UserHelper {
    fun userCursorArrayList(cursor: Cursor?): ArrayList<UsersFav> {
        val userList = ArrayList<UsersFav>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UserColumn.ID_USER))
                val username =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserColumn.NAME))
                val avaUrl =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.UserColumn.AVA_URL))

                userList.add(UsersFav(id, username, avaUrl))
            }
        }
        return userList
    }
}