package com.example.movierecommender.view.main.fragment.search

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.model.NaverMovie

interface SearchContract {
    interface View{
        fun setSearchListAdapter(list: NaverMovie?)
    }

    interface Presenter{
        fun setEnterButton(textView: TextView, searchText: String)
    }
}