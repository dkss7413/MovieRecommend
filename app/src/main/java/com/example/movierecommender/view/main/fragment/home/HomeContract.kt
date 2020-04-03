package com.example.movierecommender.view.main.fragment.home

import com.example.movierecommender.model.MovieItemDTO

interface HomeContract {
    interface View{
        fun setHomeListAdapter(list: HashMap<Int, MovieItemDTO>?)
    }

    interface Presenter{
        fun loadMovieRanking()
    }
}