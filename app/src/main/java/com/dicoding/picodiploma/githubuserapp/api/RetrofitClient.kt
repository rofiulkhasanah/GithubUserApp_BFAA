package com.dicoding.picodiploma.githubuserapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val retro = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(create())
        .build()

    val apiIns: RequestApi = retro.create(RequestApi::class.java)
}