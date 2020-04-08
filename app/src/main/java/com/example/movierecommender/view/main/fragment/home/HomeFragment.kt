package com.example.movierecommender.view.main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movierecommender.R
import com.example.movierecommender.adapter.HomeListAdapter
import com.example.movierecommender.model.MovieItemDTO
import com.example.movierecommender.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(), HomeContract.View {
    lateinit var root:View

    companion object: BaseFragment {
        override fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    fun newInstance(): Fragment{
        return HomeFragment()
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

        setHomeListAdapter(null)

        val presenter = HomePresenter().apply {
            view = this@HomeFragment
        }

        presenter.loadMovieRanking()

        return root
    }

    override fun setHomeListAdapter(list: HashMap<Int, MovieItemDTO>?) {
        val homeListAdapter = HomeListAdapter(root.homeRecyclerView, list)
        root.homeRecyclerView.adapter = homeListAdapter
        root.homeRecyclerView.layoutManager = GridLayoutManager(context, 3)
    }
}