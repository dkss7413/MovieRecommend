package com.example.movierecommender.view.main.fragments.search

interface SearchContract {
    interface View{
        fun searchEnterButton()

        fun setSearchButton()
    }

    interface Presenter{
        fun setSearchList()
    }
}