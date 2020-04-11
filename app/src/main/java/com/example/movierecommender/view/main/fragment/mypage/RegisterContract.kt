package com.example.movierecommender.view.main.fragment.mypage

interface RegisterContract {
    interface View{
        fun showToast(text: String)

        fun moveFragment(nextFragmentName: String)

        fun setUser(userId: String, nickname: String)

        fun focusOnIdtext()

        fun focusOnNicknameText()
    }

    interface Presenter{
        fun registerUser(userId: String, userPassword: String, nickname: String)
    }
}