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
import com.example.movierecommender.util.ShowFragment
import com.example.movierecommender.util.showToast
import com.example.movierecommender.view.BaseFragment
import com.example.movierecommender.view.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment :Fragment(), LoginContract.View{

    lateinit var root:View
    val presenter = LoginPresenter().apply {
        view = this@LoginFragment
    }

    companion object: BaseFragment{
        override fun newInstance(): LoginFragment{
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        root.log_loginButton.setOnClickListener {
            val userId = root.log_idText.text.toString()
            val userPassword = root.log_passwordText.text.toString()

            presenter.loginClickListner(userId, userPassword)
        }

        root.log_registerButton.setOnClickListener {
            moveFragment("register")
        }

        return root
    }

    override fun moveFragment(nextFragmentName: String){
        ShowFragment.move("login", nextFragmentName, activity!!)
    }

    override fun showToast(text: String){
        context?.showToast(text, Toast.LENGTH_SHORT)
    }

    override fun setUser(userId: String, nickname: String){
        SaveSharedPreference.setUser(context, userId, nickname)
    }
}