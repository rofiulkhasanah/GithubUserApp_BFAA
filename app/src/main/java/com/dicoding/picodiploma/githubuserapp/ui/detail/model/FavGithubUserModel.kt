package com.dicoding.picodiploma.githubuserapp.ui.detail.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.githubuserapp.data.db.FavUserDao
import com.dicoding.picodiploma.githubuserapp.data.db.FavUserGithub
import com.dicoding.picodiploma.githubuserapp.data.db.GithubUserRoomDB
import com.dicoding.picodiploma.githubuserapp.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavGithubUserModel(app: Application) : AndroidViewModel(app) {
    val usersList = MutableLiveData<User>()
    private var favUserDao: FavUserDao? = null
    private var githubUserRoomDB: GithubUserRoomDB? = null

    init {
        githubUserRoomDB = GithubUserRoomDB.getDB(app)
        favUserDao = githubUserRoomDB?.githubUserFavDao()
    }

    fun getFavGithubUser(): LiveData<List<FavUserGithub>>? = favUserDao?.getAllFavUser()

    fun addFav(logName: String, id: Int, avaGU: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavUserGithub(
                id,
                logName,
                avaGU
            )
            favUserDao?.insertFav(user)
        }
    }

    fun githubUserCheck(id: Int) = this.favUserDao?.checkUser(id)

    fun githubUserFavUncheck(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            favUserDao?.deleteUserFav(id)
        }
    }
}