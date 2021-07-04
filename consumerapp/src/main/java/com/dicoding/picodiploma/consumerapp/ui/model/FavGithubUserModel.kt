package com.dicoding.picodiploma.consumerapp.ui.model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.consumerapp.data.db.DatabaseContract
import com.dicoding.picodiploma.consumerapp.helper.UserHelper
import com.dicoding.picodiploma.consumerapp.model.UsersFav

class FavGithubUserModel(app: Application) : AndroidViewModel(app) {
    private var usersList = MutableLiveData<ArrayList<UsersFav>>()

    fun setFav(context: Context) {
        val mCursor = context.contentResolver.query(
            DatabaseContract.UserColumn.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val convertList = UserHelper.userCursorArrayList(mCursor)
        usersList.postValue(convertList)
    }

    fun getFavGithubUser(): LiveData<ArrayList<UsersFav>> {
        return usersList
    }
}