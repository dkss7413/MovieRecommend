package com.example.movierecommender.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.BuildConfig
import com.example.movierecommender.R
import com.example.movierecommender.RetrofitService
import com.example.movierecommender.adapters.SearchListAdapter
import com.example.movierecommender.models.MovieModel
import kotlinx.android.synthetic.main.fragment_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment :Fragment(){
    var searchList:MovieModel? = null
    lateinit var searchListAdapter: SearchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.searchView.setSearchListAdapter(searchList)

        view.searchText.setOnEditorActionListener{v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                val retrofit = Retrofit.Builder().apply {
                    baseUrl(RetrofitService.URL)
                    addConverterFactory(GsonConverterFactory.create())
                }.build()

                val retrofitService: RetrofitService = retrofit.create(RetrofitService::class.java)
                retrofitService.getMovie(v.text.toString()).enqueue(object : Callback<MovieModel>{
                    override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                        searchList = response.body()
                        view.searchView.setSearchListAdapter(searchList)
                    }

                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    }
                })
                true
            } else{
                false
            }
        }

        view.searchButton.setOnClickListener{
            view.searchText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }

        return view
    }

    fun RecyclerView.setSearchListAdapter(list: MovieModel?){
        searchListAdapter = SearchListAdapter(context, list)
        this.adapter = searchListAdapter
        this.layoutManager = LinearLayoutManager(activity)
    }
}