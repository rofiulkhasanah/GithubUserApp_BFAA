package com.dicoding.picodiploma.consumerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.consumerapp.adapter.FavUserAdapter
import com.dicoding.picodiploma.consumerapp.databinding.ActivityMainBinding
import com.dicoding.picodiploma.consumerapp.model.UsersFav
import com.dicoding.picodiploma.consumerapp.ui.model.FavGithubUserModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: FavGithubUserModel
    private lateinit var adapterList: FavUserAdapter
    private var list: ArrayList<UsersFav> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = resources.getString(R.string.fav_user)

        adapterList = FavUserAdapter(list)
        adapterList.notifyDataSetChanged()
        model = ViewModelProvider(this).get(FavGithubUserModel::class.java)
        rvShow()
        model.setFav(this)
        model.getFavGithubUser().observe(this, {
            if (it != null) {
                adapterList.onList(it)
            }
        })
    }

    private fun rvShow() {
        binding.apply {
            rvGithubUsersFav.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGithubUsersFav.setHasFixedSize(true)
            rvGithubUsersFav.adapter = adapterList
        }
    }
}