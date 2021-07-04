package com.dicoding.picodiploma.githubuserapp.ui.detail.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.githubuserapp.api.RetrofitClient
import com.dicoding.picodiploma.githubuserapp.data.db.FavUserDao
import com.dicoding.picodiploma.githubuserapp.data.db.FavUserGithub
import com.dicoding.picodiploma.githubuserapp.data.db.GithubUserRoomDB
import com.dicoding.picodiploma.githubuserapp.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailGithubUserModel(app: Application) : AndroidViewModel(app) {
    val usersList = MutableLiveData<User>()
    private var favUserDao: FavUserDao? = null
    private var githubUserRoomDB: GithubUserRoomDB? = null

    init {
        githubUserRoomDB = GithubUserRoomDB.getDB(app)
        favUserDao = githubUserRoomDB?.githubUserFavDao()
    }

    fun setGithubUserDetail(username: String) {
        RetrofitClient.apiIns
            .detailUserApi(username)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        usersList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getGithubUserDetail(): LiveData<User> {
        return usersList
    }

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

