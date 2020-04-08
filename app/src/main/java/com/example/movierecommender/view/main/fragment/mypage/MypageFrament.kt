package com.example.movierecommender.view.main.fragment.mypage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.ShowFragment
import com.example.movierecommender.util.replaceFragment
import com.example.movierecommender.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_mypage.view.*

class MypageFrament:Fragment(){
    companion object: BaseFragment{
        override fun newInstance(): MypageFrament{
            return MypageFrament()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_mypage, container, false)

        root.logoutButton.setOnClickListener {
            SaveSharedPreference.clearUser(context)
            ShowFragment.move("mypage", "login", activity!!)
        }

        return root
    }
}