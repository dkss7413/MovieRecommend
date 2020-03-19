package com.example.movierecommender.view.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movierecommender.BuildConfig
import com.example.movierecommender.R
import com.example.movierecommender.network.BoxOfficeAPI
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val timeFormat = SimpleDateFormat("yyyyMMdd")
        val currentTime = timeFormat.format(Date()).toInt()

        val dispose = BoxOfficeAPI.create().getMovie(BuildConfig.Box_Office_Key, currentTime - 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({Log.d("결과", it.getMovieName(0))
            }, {})



        return root
    }

    fun JsonObject.getMovieName(num: Int): String {
        return this.getAsJsonObject("boxOfficeResult").getAsJsonArray("dailyBoxOfficeList").get(0)
            .asJsonObject.get("movieNm").asString
    }
}