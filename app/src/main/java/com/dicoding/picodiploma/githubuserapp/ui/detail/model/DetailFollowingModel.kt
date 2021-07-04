package com.dicoding.picodiploma.githubuserapp.ui.detail.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.githubuserapp.api.RetrofitClient
import com.dicoding.picodiploma.githubuserapp.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFollowingModel : ViewModel() {

    val userFollowingList = MutableLiveData<ArrayList<User>>()

    fun setFollowing(username: String) {
        RetrofitClient.apiIns
            .listFollowingApi(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        userFollowingList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getFollowing(): LiveData<ArrayList<User>> {
        return userFollowingList
    }
}