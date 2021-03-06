package com.example.movierecommender.view.main.fragment.home

import com.example.movierecommender.BuildConfig
import com.example.movierecommender.model.MovieItemDTO
import com.example.movierecommender.network.BoxOfficeAPI
import com.example.movierecommender.network.NaverAPI
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HomePresenter : HomeContract.Presenter {
    lateinit var view: HomeContract.View
    lateinit var movieMap: HashMap<Int, MovieItemDTO>

    override fun loadMovieRanking() {
        movieMap = HashMap()
        var num = -1

        val dispose =
            BoxOfficeAPI.create().getBoxOffice(BuildConfig.Box_Office_Key, getCurrentTime() - 1)
                .map { it.extractMovieName() }
                .flatMap({ Observable.range(0, 9) },
                    { severalMovieName, num -> severalMovieName.get(num) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    num++

                    getMoviePoster(it, num)
                }, {}, {})
    }

    fun getMoviePoster(title: String, num: Int){
        NaverAPI.create().getMovie(title, 1)
            .map { it.items.get(0) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                movieMap[num] = it
                if (movieMap.size == 9) {
                    view.setHomeListAdapter(movieMap)
                }
            }, {})
    }

    fun JsonObject.extractMovieName(): ArrayList<String> {
        val arrayList = ArrayList<String>()

        for (i in 0..8)
            arrayList.add(
                this.getAsJsonObject("boxOfficeResult").getAsJsonArray("dailyBoxOfficeList").get(i)
                    .asJsonObject.get("movieNm").asString
            )
        return arrayList
    }

    fun getCurrentTime(): Int {
        return SimpleDateFormat("yyyyMMdd").format(Date()).toInt()
    }
}