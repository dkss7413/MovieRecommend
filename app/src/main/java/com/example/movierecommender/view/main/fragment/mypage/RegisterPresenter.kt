package com.example.movierecommender.view.main.fragment.mypage

import android.util.Log
import com.example.movierecommender.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterPresenter: RegisterContract.Presenter{
    lateinit var view: RegisterContract.View

    override fun registerUser(userId: String, userPassword: String, nickname: String){
        Service.create().userRegister(userId, userPassword, nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                when(it.get("result").asString){
                    "Id error!" -> {
                        view.showToast("존재하는 아이디입니다.")
                        view.focusOnIdtext()
                    }
                    "Nickname error!" -> {
                        view.showToast("존재하는 닉네임입니다.")
                        view.focusOnNicknameText()
                    }
                    "true" -> {
                        view.showToast("회원가입 성공")

                        view.setUser(userId, nickname)
                        view.moveFragment("mypage")
                    }
                }

            }, { Log.d("회원가입 오류", it.localizedMessage) })
    }
}