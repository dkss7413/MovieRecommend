package com.example.movierecommender.view.main.fragment.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.network.Service
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.replaceFragment
import com.example.movierecommender.util.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

        root.log_loginButton.setOnClickListener {
            val userId = root.log_idText.text.toString()
            val userPassword = root.log_passwordText.text.toString()

            Service.create().userLogin(userId, userPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    when(it.get("result").asString){
                        "true" -> {
                            SaveSharedPreference.setUser(context, it.get("userId").asString, it.get("nickname").asString)
                            MypageFrament.newInstance().replaceFragment(activity)
                        }
                        "false" -> context?.showToast("아이디와 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT)
                    }

                }, { Log.d("로그인 오류", it.localizedMessage) })
        }

        root.log_registerButton.setOnClickListener {
            registerFragment.replaceFragment(activity)
        }

        return root
    }
}