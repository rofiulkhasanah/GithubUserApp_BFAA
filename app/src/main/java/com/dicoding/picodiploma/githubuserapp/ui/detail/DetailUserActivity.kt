package com.dicoding.picodiploma.githubuserapp.ui.detail

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.githubuserapp.R
import com.dicoding.picodiploma.githubuserapp.adapter.SectionsPagerAdapter
import com.dicoding.picodiploma.githubuserapp.databinding.ActivityDetailUserBinding
import com.dicoding.picodiploma.githubuserapp.ui.detail.model.DetailGithubUserModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVA = "extra_ava"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailModel: DetailGithubUserModel
    var checked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar


        val username: String = intent.getStringExtra(EXTRA_USER).toString()
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avaGithubUser = intent.getStringExtra(EXTRA_AVA).toString()
        val bundle = Bundle()
        bundle.putString(EXTRA_USER, username)

        detailModel = ViewModelProvider(this).get(DetailGithubUserModel::class.java)
        detailModel.setGithubUserDetail(username)
        detailModel.getGithubUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .apply(RequestOptions().override(150, 150))
                        .into(imgPhoto)
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvLocation.text = it.location
                    tvCompany.text = it.company
                    tvFollowers.text = "${it.followers}"
                    tvFollowing.text = "${it.following}"
                    tvRepository.text = "${it.public_repos}"
                }
                actionBar?.title = resources.getString(R.string.bar_detail, it.name)
                actionBar?.setDisplayHomeAsUpEnabled(true)
            }
        })

        CoroutineScope(Dispatchers.IO).launch {
            val final = id.let { detailModel.githubUserCheck(it) }
            withContext(Dispatchers.Main) {
                if (final != null) {
                    if (final > 0) {
                        true.also { binding.tbFav.isChecked = it }
                        checked = true
                    } else {
                        false.also { binding.tbFav.isChecked = it }
                        checked = false
                    }
                }
            }
        }

        binding.tbFav.setOnClickListener {
            checked = !checked
            if (checked) {
                detailModel.addFav(username, id, avaGithubUser)
            } else {
                detailModel.githubUserFavUncheck(id)
            }
            binding.tbFav.isChecked = checked
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}