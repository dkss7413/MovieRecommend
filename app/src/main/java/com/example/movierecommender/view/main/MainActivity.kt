package com.example.movierecommender.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.ShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ShowFragment.show("home", this)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> ShowFragment.show("home", this)
                R.id.menu_search -> ShowFragment.show("search", this)
                R.id.menu_community -> ShowFragment.show("community", this)
                R.id.menu_mypge -> {
                    if (SaveSharedPreference.getUserId(this)?.length == 0) {
                        ShowFragment.show("login", this)
                    } else {
                        ShowFragment.show("mypage", this)
                    }
                }
            }
            true
        }
    }
}