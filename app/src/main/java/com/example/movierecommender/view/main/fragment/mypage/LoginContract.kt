package com.example.movierecommender.view.main.fragment.mypage

interface LoginContract {
    interface View{
        fun moveFragment(nextFragmentName: String)

        fun showToast(text: String)

        fun setUser(userId: String, nickname: String)
    }

    interface Presenter{
        fun loginClickListner(userId: String, userPassword: String)
    }
}