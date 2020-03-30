package com.example.movierecommender.view.main.fragments.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.view.main.replaceFragment
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment(){
    companion object{
        fun newInstance(): RegisterFragment{
            return RegisterFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        root.registerButton2.setOnClickListener {

        }

        root.cancelButton.setOnClickListener {
            LoginFragment.newInstance().replaceFragment(activity)
        }

        return root
    }
}