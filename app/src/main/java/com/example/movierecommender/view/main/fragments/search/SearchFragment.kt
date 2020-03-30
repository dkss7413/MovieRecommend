package com.example.movierecommender.view.main.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.R
import com.example.movierecommender.adapters.SearchListAdapter
import com.example.movierecommender.models.NaverMovie
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(), SearchContract.View {
    lateinit var root: View
    lateinit var searchListAdapter: SearchListAdapter

    companion object{
        fun newInstance(): SearchFragment{
            return SearchFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_search, container, false)

        setSearchListAdapter(root.searchRecyclerView, null)


        val presenter = SearchPresenter().apply {
            this.view = this@SearchFragment
            this.recyclerView = root.searchRecyclerView
        }

        root.searchText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.setEnterButton(v, root.searchText.text.toString())
                true
            } else {
                false
            }
        }

        root.searchButton.setOnClickListener {
            root.searchText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }

        return root
    }

    override fun setSearchListAdapter(view: RecyclerView, list: NaverMovie?) {
        searchListAdapter = SearchListAdapter(list)
        view.adapter = searchListAdapter
        view.layoutManager = LinearLayoutManager(activity)
    }
}