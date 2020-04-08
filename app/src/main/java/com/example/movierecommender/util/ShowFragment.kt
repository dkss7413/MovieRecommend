package com.example.movierecommender.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.movierecommender.R
import com.example.movierecommender.view.BaseFragment
import com.example.movierecommender.view.main.fragment.community.BoardAddFragment
import com.example.movierecommender.view.main.fragment.community.CommunityFragment
import com.example.movierecommender.view.main.fragment.home.HomeFragment
import com.example.movierecommender.view.main.fragment.mypage.LoginFragment
import com.example.movierecommender.view.main.fragment.mypage.MypageFrament
import com.example.movierecommender.view.main.fragment.mypage.RegisterFragment
import com.example.movierecommender.view.main.fragment.search.SearchFragment

class ShowFragment {
    companion object {
        private val fragmentMap: HashMap<String, Fragment> = HashMap()

        fun show(fragmentName: String, activity: FragmentActivity) {
            fragmentMap.forEach { string, fragment ->
                activity.supportFragmentManager.beginTransaction().hide(fragment).commit()
            }

            when (fragmentName) {
                "home" -> HomeFragment.set(fragmentName, activity)
                "search" -> SearchFragment.set(fragmentName, activity)
                "community" -> CommunityFragment.set(fragmentName, activity)
                "mypage" -> MypageFrament.set(fragmentName, activity)
                "login" -> LoginFragment.set(fragmentName, activity)
                "register" -> RegisterFragment.set(fragmentName, activity)
                "boardAdd" -> BoardAddFragment.set(fragmentName, activity)
                else -> {
                }
            }
        }

        fun move(currentFragmentName: String, nextFragmentName: String, activity: FragmentActivity){
            show(nextFragmentName, activity)
            fragmentMap.remove(currentFragmentName)
        }

        private fun BaseFragment.set(fragmentName: String, activity: FragmentActivity) {
            if (fragmentMap[fragmentName] == null) {
                fragmentMap[fragmentName] = this.newInstance()
                activity.supportFragmentManager.beginTransaction()
                    .add(R.id.frame_layout, fragmentMap[fragmentName]!!).commit()
            } else {
                activity.supportFragmentManager.beginTransaction().show(fragmentMap[fragmentName]!!)
                    .commit()
            }
        }
    }
}