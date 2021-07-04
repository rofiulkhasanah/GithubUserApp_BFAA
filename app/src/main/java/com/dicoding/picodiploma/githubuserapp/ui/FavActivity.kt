package com.dicoding.picodiploma.githubuserapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.githubuserapp.R
import com.dicoding.picodiploma.githubuserapp.adapter.FavUserAdapter
import com.dicoding.picodiploma.githubuserapp.data.db.FavUserGithub
import com.dicoding.picodiploma.githubuserapp.data.model.UsersFav
import com.dicoding.picodiploma.githubuserapp.databinding.ActivityFavBinding
import com.dicoding.picodiploma.githubuserapp.databinding.ItemRowUsersBinding
import com.dicoding.picodiploma.githubuserapp.ui.detail.DetailUserActivity
import com.dicoding.picodiploma.githubuserapp.ui.detail.model.FavGithubUserModel

class FavActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVA = "extra_ava"
    }

    private lateinit var binding: ActivityFavBinding
    private lateinit var bindingItem: ItemRowUsersBinding
    private lateinit var model: FavGithubUserModel
    private lateinit var adapterList: FavUserAdapter
    private var list: ArrayList<UsersFav> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = resources.getString(R.string.fav_user)

        adapterList = FavUserAdapter(list)
        adapterList.notifyDataSetChanged()
        model = ViewModelProvider(this).get(FavGithubUserModel::class.java)

        adapterList.setGithubUserClick(object : FavUserAdapter.OnItemListener {
            override fun onUserClick(listUser: UsersFav, position: Int) {
                Intent(this@FavActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USER, listUser.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, listUser.id_user)
                    it.putExtra(DetailUserActivity.EXTRA_AVA, listUser.avatar_url)
                    startActivity(it)
                }
            }

        })

        rvShow()

        model.getFavGithubUser()?.observe(this, {
            if (it != null) {
                val list = dataList(it)
                adapterList.onList(list)
            }
        })
    }

    private fun dataList(githubUser: List<FavUserGithub>): ArrayList<UsersFav> {
        val listUsers = ArrayList<UsersFav>()
        for (user in githubUser) {
            val userMapped = UsersFav(user.id_user, user.login, user.avatar_url)
            listUsers.add(userMapped)
        }
        return listUsers
    }

    private fun rvShow() {
        binding.apply {
            rvGithubUsersFav.layoutManager = LinearLayoutManager(this@FavActivity)
            rvGithubUsersFav.setHasFixedSize(true)
            rvGithubUsersFav.adapter = adapterList
        }
    }
}