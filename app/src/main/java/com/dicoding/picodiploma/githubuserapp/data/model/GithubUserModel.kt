package com.dicoding.picodiploma.githubuserapp.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.githubuserapp.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserModel : ViewModel() {
    val usersList = MutableLiveData<ArrayList<User>>()

    fun setUserSearch(searchName: String) {
        RetrofitClient.apiIns
            .userSearchApi(searchName)
            .enqueue(object : Callback<Users> {
                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                    if (response.isSuccessful) {
                        usersList.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getUserSearch(): LiveData<ArrayList<User>> {
        return usersList
    }
}