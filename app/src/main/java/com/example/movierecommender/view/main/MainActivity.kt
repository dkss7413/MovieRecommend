package com.example.movierecommender.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.movierecommender.R
import com.example.movierecommender.util.replaceFragment
import com.example.movierecommender.view.main.fragment.community.CommunityFragment
import com.example.movierecommender.view.main.fragment.home.HomeFragment
import com.example.movierecommender.view.main.fragment.mypage.MypageFrament
import com.example.movierecommender.view.main.fragment.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_community.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> HomeFragment.newInstance().replaceFragment(this)
                R.id.menu_search -> SearchFragment.newInstance().replaceFragment(this)
                R.id.menu_community -> CommunityFragment.newInstance().replaceFragment(this)
                R.id.menu_mypge -> MypageFrament.newInstance().replaceFragment(this)
            }
            true
        }
    }
}