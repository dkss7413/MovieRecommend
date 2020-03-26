package com.example.movierecommender.view.main.fragments.search

import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.network.NaverAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchPresenter : SearchContract.Presenter{
    lateinit var view: SearchContract.View
    lateinit var recyclerView: RecyclerView

    override fun setEnterButton(textView: TextView, searchText: String) {
        NaverAPI.create().getMovie(searchText, 10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({searchList -> view.setSearchListAdapter(recyclerView, searchList)},
                {})
    }
}