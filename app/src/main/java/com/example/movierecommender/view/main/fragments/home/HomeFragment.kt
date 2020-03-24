package com.example.movierecommender.view.main.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movierecommender.BuildConfig
import com.example.movierecommender.R
import com.example.movierecommender.adapters.HomeListAdapter
import com.example.movierecommender.models.NaverMovieItem
import com.example.movierecommender.network.BoxOfficeAPI
import com.example.movierecommender.network.NaverAPI
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment(), HomeContract.View {
    var movieList: ArrayList<NaverMovieItem>? = null
    lateinit var root:View
    lateinit var homeListAdapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        movieList = ArrayList()

        setHomeListAdapter()

        val presenter = HomePresenter().apply {
            view = this@HomeFragment
            adapterMovieList = movieList
        }

        presenter.loadMovieRanking()

        return root
    }

    override fun setHomeListAdapter() {
        homeListAdapter = HomeListAdapter(root, movieList)
        root.homeView.adapter = homeListAdapter
        root.homeView.layoutManager = GridLayoutManager(context, 3)
    }
}