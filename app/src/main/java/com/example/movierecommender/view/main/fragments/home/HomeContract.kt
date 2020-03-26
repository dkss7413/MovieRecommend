package com.example.movierecommender.view.main.fragments.home

import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.models.NaverMovieItem

interface HomeContract {
    interface View{
        fun setHomeListAdapter(view: RecyclerView, list: HashMap<Int, NaverMovieItem>?)
    }

    interface Presenter{
        fun loadMovieRanking()
    }
}