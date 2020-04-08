package com.example.movierecommender.view.main.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierecommender.R
import com.example.movierecommender.adapter.SearchListAdapter
import com.example.movierecommender.model.MovieDTO
import com.example.movierecommender.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(), SearchContract.View {
    lateinit var root: View
    lateinit var searchListAdapter: SearchListAdapter

    companion object: BaseFragment {
        override fun newInstance(): SearchFragment{
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

        setSearchListAdapter(null)

        val presenter = SearchPresenter().apply {
            this.view = this@SearchFragment
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

    override fun setSearchListAdapter(list: MovieDTO?) {
        searchListAdapter = SearchListAdapter(list)
        root.searchRecyclerView.adapter = searchListAdapter
        root.searchRecyclerView.layoutManager = LinearLayoutManager(activity)
    }
}