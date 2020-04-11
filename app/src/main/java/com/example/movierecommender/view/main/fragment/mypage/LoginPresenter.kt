package com.example.movierecommender.view.main.fragment.mypage

import android.util.Log
import com.example.movierecommender.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter: LoginContract.Presenter {
    lateinit var view: LoginContract.View

    override fun loginClickListner(userId: String, userPassword: String){
        Service.create().userLogin(userId, userPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                when(it.get("result").asString){
                    "true" -> {
                        view.setUser(it.get("userId").asString, it.get("nickname").asString)
                        view.moveFragment("mypage")
                    }
                    "false" -> view.showToast("아이디와 비밀번호를 확인해 주세요.")
                }

            }, { Log.d("로그인 오류", it.localizedMessage) })
    }
}