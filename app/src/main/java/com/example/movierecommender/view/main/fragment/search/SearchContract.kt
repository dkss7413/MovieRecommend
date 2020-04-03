package com.example.movierecommender.view.main.fragment.search

import android.widget.TextView
import com.example.movierecommender.model.MovieDTO

interface SearchContract {
    interface View{
        fun setSearchListAdapter(list: MovieDTO?)
    }

    interface Presenter{
        fun setEnterButton(textView: TextView, searchText: String)
    }
}