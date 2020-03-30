package com.example.movierecommender.view.main.fragments.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.view.main.MainActivity
import com.example.movierecommender.view.main.replaceFragment
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment :Fragment(){

    lateinit var root:View
    lateinit var registerFragment: RegisterFragment

    companion object{
        fun newInstance(): LoginFragment{
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        registerFragment = RegisterFragment()

        root.loginButton.setOnClickListener {

        }

        root.registerButton.setOnClickListener {
            registerFragment.replaceFragment(activity)
        }

        return root
    }
}