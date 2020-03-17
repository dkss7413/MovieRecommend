package com.example.movierecommender.view.main.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.network.NaverAPI
import com.example.movierecommender.R
import com.example.movierecommender.adapters.SearchListAdapter
import com.example.movierecommender.models.MovieModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(), SearchContract.View {
    lateinit var root: View
    lateinit var searchListAdapter: SearchListAdapter
    lateinit var emptyList: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_search, container, false)

        root.searchView.setSearchListAdapter(null)

        val presenter = SearchPresenter().apply {
            this.view = this@SearchFragment
        }

        presenter.setSearchList()

        return root
    }


    override fun searchEnterButton() {
        root.searchText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var disposal = NaverAPI.create().getMovie(v.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{searchList -> root.searchView.setSearchListAdapter(searchList)}
            true
            } else {
                false
            }
        }
    }

    override fun setSearchButton() {
        root.searchButton.setOnClickListener {
            root.searchText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }
    }

    fun RecyclerView.setSearchListAdapter(list: MovieModel?) {
        searchListAdapter = SearchListAdapter(context, list)
        this.adapter = searchListAdapter
        this.layoutManager = LinearLayoutManager(activity)
    }
}