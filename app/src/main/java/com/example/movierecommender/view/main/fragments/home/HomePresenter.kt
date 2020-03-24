package com.example.movierecommender.view.main.fragments.home

import androidx.recyclerview.widget.GridLayoutManager
import com.example.movierecommender.BuildConfig
import com.example.movierecommender.adapters.HomeListAdapter
import com.example.movierecommender.models.NaverMovieItem
import com.example.movierecommender.network.BoxOfficeAPI
import com.example.movierecommender.network.NaverAPI
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomePresenter : HomeContract.Presenter {
    lateinit var view: HomeContract.View
    var adapterMovieList: ArrayList<NaverMovieItem>? = null

    override fun loadMovieRanking() {
        val dispose =
            BoxOfficeAPI.create().getBoxOffice(BuildConfig.Box_Office_Key, getCurrentTime() - 1)
                .map { it.getSeveralMovieName() }
                .flatMap({ Observable.range(0, 9) },
                    { severalMovieName, num -> severalMovieName.get(num) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    NaverAPI.create().getMovie(it)
                        .map { it.items.get(0) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            adapterMovieList?.add(it)
                            if (adapterMovieList?.size == 9) {
                                view.setHomeListAdapter()
                            }
                        }, {})
                }, {}, {})
    }

    fun JsonObject.getSeveralMovieName(): ArrayList<String> {
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