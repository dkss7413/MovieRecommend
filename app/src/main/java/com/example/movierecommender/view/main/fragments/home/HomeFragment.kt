package com.example.movierecommender.view.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.R
import com.example.movierecommender.adapters.HomeListAdapter
import com.example.movierecommender.models.NaverMovieItem
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), HomeContract.View {
    lateinit var root:View

    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
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
        root = inflater.inflate(R.layout.fragment_home, container, false)

        setHomeListAdapter(root.homeRecyclerView, null)

        val presenter = HomePresenter().apply {
            view = this@HomeFragment
            recyclerView = root.homeRecyclerView
        }

        presenter.loadMovieRanking()

        return root
    }

    override fun setHomeListAdapter(view: RecyclerView, list: HashMap<Int, NaverMovieItem>?) {
        val homeListAdapter = HomeListAdapter(view, list)
        view.homeRecyclerView.adapter = homeListAdapter
        view.homeRecyclerView.layoutManager = GridLayoutManager(context, 3)
    }
}