package com.example.movierecommender.network

import android.util.Log
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BoxOfficeAPI {
    companion object{
        val BASE_URL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

        fun create(): BoxOfficeAPI{
            val httpLoggingInterceptor = HttpLoggingInterceptor (HttpLoggingInterceptor.Logger {
                Log.d("박스오피스 통신 로그", it)
            })
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BoxOfficeAPI::class.java)
        }
    }

    @GET("searchDailyBoxOfficeList.json")
    fun getBoxOffice(
        @Query("key") key:String,
        @Query("targetDt") date:Int
    ) : Observable<JsonObject>
}