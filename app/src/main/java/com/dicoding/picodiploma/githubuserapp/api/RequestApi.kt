package com.dicoding.picodiploma.githubuserapp.api

import com.dicoding.picodiploma.githubuserapp.data.model.User
import com.dicoding.picodiploma.githubuserapp.data.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestApi {
    // Search User
    @GET("search/users")
    @Headers("Authorization: token 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")

    fun userSearchApi(
        @Query("q") login: String
    ): Call<Users>

    // Detail User
    @GET("users/{username}")
    @Headers("Authorization: token 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")

    fun detailUserApi(@Path("username") username: String): Call<User>

    // List Follower
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")

    fun listFollowerApi(@Path("username") username: String): Call<ArrayList<User>>

    // List Following
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")

    fun listFollowingApi(@Path("username") username: String): Call<ArrayList<User>>
}