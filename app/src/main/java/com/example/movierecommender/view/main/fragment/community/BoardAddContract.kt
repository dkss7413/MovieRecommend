package com.example.movierecommender.view.main.fragment.community

interface BoardAddContract {
    interface View {
        fun showToast(text: String)

        fun moveFragment(nextFragmentName: String)
    }

    interface Presenter {
        fun addBoard(boardData: HashMap<String, String?>)
    }
}