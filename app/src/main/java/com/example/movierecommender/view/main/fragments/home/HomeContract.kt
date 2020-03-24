package com.example.movierecommender.view.main.fragments.home

interface HomeContract {
    interface View{
        fun setHomeListAdapter()
    }

    interface Presenter{
        fun loadMovieRanking()
    }
}