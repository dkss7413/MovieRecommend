package com.example.movierecommender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.movierecommender.fragments.CommunityFragment
import com.example.movierecommender.fragments.HomeFragment
import com.example.movierecommender.fragments.MypageFrament
import com.example.movierecommender.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragment = supportFragmentManager
    private lateinit var homeFragment: HomeFragment
    private lateinit var communityFragment: CommunityFragment
    private lateinit var mypageFrament: MypageFrament
    private lateinit var  searchFragment: SearchFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeFragment = HomeFragment()
        communityFragment =
            CommunityFragment()
        mypageFrament = MypageFrament()
        searchFragment = SearchFragment()

        val transaction = fragment.beginTransaction()
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss()

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            val transaction = fragment.beginTransaction()

            when(it.itemId){
                R.id.menu_home -> transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss()
                R.id.menu_community -> transaction.replace(R.id.frame_layout, communityFragment).commitAllowingStateLoss()
                R.id.menu_mypge -> transaction.replace(R.id.frame_layout, mypageFrament).commitAllowingStateLoss()
                R.id.menu_search -> transaction.replace(R.id.frame_layout, searchFragment).commitAllowingStateLoss()
            }
            true
        }
    }
}
