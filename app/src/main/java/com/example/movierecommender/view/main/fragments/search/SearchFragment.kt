package com.example.movierecommender.view.main.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.R
import com.example.movierecommender.RetrofitService
import com.example.movierecommender.adapters.SearchListAdapter
import com.example.movierecommender.models.MovieModel
import com.example.movierecommender.view.main.MainContract
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment :Fragment(), SearchContract.View{
    var searchList:MovieModel? = null
    lateinit var root: View
    lateinit var searchListAdapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_search, container, false)

        root.searchView.setSearchListAdapter(searchList)

        val presenter = SearchPresenter().apply {
            this.view = this@SearchFragment
        }

        presenter.setSearchList()

        return root
    }


    override fun searchEnterButton(){
        root.searchText.setOnEditorActionListener{v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                val retrofit = Retrofit.Builder().apply {
                    baseUrl(RetrofitService.URL)
                    addConverterFactory(GsonConverterFactory.create())
                }.build()

                val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)
                retrofitService.getMovie(v.text.toString()).enqueue(object : Callback<MovieModel>{
                    override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                        searchList = response.body()
                        root.searchView.setSearchListAdapter(searchList)
                    }

                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    }
                })
                true
            } else{
                false
            }
        }
    }

    override fun setSearchButton(){
        root.searchButton.setOnClickListener{
            root.searchText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }
    }

    fun RecyclerView.setSearchListAdapter(list: MovieModel?){
        searchListAdapter = SearchListAdapter(context, list)
        this.adapter = searchListAdapter
        this.layoutManager = LinearLayoutManager(activity)
    }
}