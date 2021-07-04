package com.dicoding.picodiploma.githubuserapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.githubuserapp.R
import com.dicoding.picodiploma.githubuserapp.adapter.ListUserAdapter
import com.dicoding.picodiploma.githubuserapp.data.model.User
import com.dicoding.picodiploma.githubuserapp.databinding.FragmentFollowersBinding
import com.dicoding.picodiploma.githubuserapp.ui.detail.model.DetailFollowersModel

class FollowersFragment : Fragment(R.layout.fragment_followers) {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding
    private lateinit var followersModel: DetailFollowersModel
    private lateinit var adapter: ListUserAdapter
    private lateinit var username: String
    private var list: ArrayList<User> = arrayListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arg = arguments
        username = arg?.getString(DetailUserActivity.EXTRA_USER).toString()
        _binding = FragmentFollowersBinding.bind(view)

        adapter = ListUserAdapter(list)
        adapter.notifyDataSetChanged()

        binding?.apply {
            rvUserFollowers.setHasFixedSize(true)
            rvUserFollowers.layoutManager = LinearLayoutManager(activity)
            rvUserFollowers.adapter = adapter
        }
        onLoading(true)
        followersModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailFollowersModel::class.java)
        followersModel.setFollowers(username)
        followersModel.getFollowers().observe(viewLifecycleOwner, {
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