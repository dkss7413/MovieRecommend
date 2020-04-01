package com.example.movierecommender.view.main.fragment.home

import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.model.NaverMovieItem

interface HomeContract {
    interface View{
        fun setHomeListAdapter(list: HashMap<Int, NaverMovieItem>?)
    }

    interface Presenter{
        fun loadMovieRanking()
    }
}