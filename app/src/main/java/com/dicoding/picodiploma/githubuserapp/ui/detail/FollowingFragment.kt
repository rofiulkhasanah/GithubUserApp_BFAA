package com.dicoding.picodiploma.githubuserapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.githubuserapp.R
import com.dicoding.picodiploma.githubuserapp.adapter.ListUserAdapter
import com.dicoding.picodiploma.githubuserapp.data.model.User
import com.dicoding.picodiploma.githubuserapp.databinding.FragmentFollowingBinding
import com.dicoding.picodiploma.githubuserapp.ui.detail.model.DetailFollowingModel

class FollowingFragment : Fragment(R.layout.fragment_following) {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding
    private lateinit var followingModel: DetailFollowingModel
    private lateinit var adapter: ListUserAdapter
    private lateinit var username: String
    private var list: ArrayList<User> = arrayListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arg = arguments
        username = arg?.getString(DetailUserActivity.EXTRA_USER).toString()
        _binding = FragmentFollowingBinding.bind(view)

        adapter = ListUserAdapter(list)
        adapter.notifyDataSetChanged()

        binding?.apply {
            rvUserFollowing.setHasFixedSize(true)
            rvUserFollowing.layoutManager = LinearLayoutManager(activity)
            rvUserFollowing.adapter = adapter
        }
        onLoading(true)
        followingModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailFollowingModel::class.java
        )
        followingModel.setFollowing(username)
        followingModel.getFollowing().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.onList(it)
                onLoading(false)
            }
        })
    }

    private fun onLoading(status: Boolean) {
        if (status) {
            binding?.pb?.visibility = View.VISIBLE
        } else {
            binding?.pb?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}