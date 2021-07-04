package com.dicoding.picodiploma.githubuserapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.githubuserapp.adapter.ListUserAdapter
import com.dicoding.picodiploma.githubuserapp.data.model.GithubUserModel
import com.dicoding.picodiploma.githubuserapp.data.model.User
import com.dicoding.picodiploma.githubuserapp.databinding.ActivityMainBinding
import com.dicoding.picodiploma.githubuserapp.ui.FavActivity
import com.dicoding.picodiploma.githubuserapp.ui.SettingActivity
import com.dicoding.picodiploma.githubuserapp.ui.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: GithubUserModel
    private lateinit var adapterList: ListUserAdapter
    private var list: ArrayList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = resources.getString(R.string.bar_search)

        adapterList = ListUserAdapter(list)
        adapterList.notifyDataSetChanged()
        adapterList.setGithubUserClick(object : ListUserAdapter.OnItemListener {
            override fun onUserClick(listUser: User, position: Int) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USER, listUser.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, listUser.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVA, listUser.avatar_url)
                    startActivity(it)
                }
            }

        })

        model = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(GithubUserModel::class.java)

        binding.apply {
            rvGithubUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGithubUsers.setHasFixedSize(true)
            rvGithubUsers.adapter = adapterList

            btnImgSearch.setOnClickListener {
                onUserSearch()
            }
            edtName.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    onUserSearch()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        model.getUserSearch().observe(this, {
            if (it.isNotEmpty()) {
                adapterList.onList(it)
                onLoading(false)
            } else {
                val messageEmpty = "Data Tidak Ditemukan"
                Toast.makeText(applicationContext, messageEmpty, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onLoading(status: Boolean) {
        if (status) {
            binding.pb.visibility = View.VISIBLE
        } else {
            binding.pb.visibility = View.GONE
        }
    }

    private fun onUserSearch() {
        binding.apply {
            val searchData = edtName.text.toString()
            if (searchData.isEmpty()) return onLoading(true)
            model.setUserSearch(searchData)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                val mIntent = Intent(this, FavActivity::class.java)
                startActivity(mIntent)
            }
            R.id.action_setting -> {
                val mIntent = Intent(this, SettingActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}