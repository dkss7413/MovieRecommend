package com.example.movierecommender.view.main

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.movierecommender.R
import com.example.movierecommender.view.main.fragments.community.CommunityFragment
import com.example.movierecommender.view.main.fragments.mypage.LoginFragment
import com.example.movierecommender.view.main.fragments.home.HomeFragment
import com.example.movierecommender.view.main.fragments.mypage.MypageFrament
import com.example.movierecommender.view.main.fragments.mypage.RegisterFragment
import com.example.movierecommender.view.main.fragments.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HomeFragment.newInstance().replaceFragment(this)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> HomeFragment.newInstance().replaceFragment(this)
                R.id.menu_community -> CommunityFragment.newInstance().replaceFragment(this)
                R.id.menu_search -> SearchFragment.newInstance().replaceFragment(this)
                R.id.menu_mypge -> LoginFragment.newInstance().replaceFragment(this)
            }
            true
        }
    }
}

fun Fragment.replaceFragment(activity: FragmentActivity?) {
    val fragment = activity?.supportFragmentManager
    val transaction = fragment?.beginTransaction()
    transaction?.replace(R.id.frame_layout, this)?.commitAllowingStateLoss()
}