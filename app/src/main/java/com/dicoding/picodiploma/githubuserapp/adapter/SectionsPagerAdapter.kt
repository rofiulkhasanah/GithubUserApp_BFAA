package com.dicoding.picodiploma.githubuserapp.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.picodiploma.githubuserapp.ui.detail.FollowersFragment
import com.dicoding.picodiploma.githubuserapp.ui.detail.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity, userData: Bundle) :
    FragmentStateAdapter(activity) {
    private var fragmentBundle = userData

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }
}