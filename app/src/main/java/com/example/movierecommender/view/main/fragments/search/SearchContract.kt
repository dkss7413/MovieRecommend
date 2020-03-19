package com.example.movierecommender.view.main.fragments.search

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.models.NaverMovie

interface SearchContract {
    interface View{
        fun setSearchListAdapter(view: RecyclerView, list: NaverMovie?)
    }

    interface Presenter{
        fun setEnterButton(textView: TextView, searchText: String)
    }
}