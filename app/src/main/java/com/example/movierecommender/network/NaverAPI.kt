package com.example.movierecommender.network

import android.util.Log
import com.example.movierecommender.BuildConfig
import com.example.movierecommender.models.NaverMovie
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverAPI {
    companion object {
        val BASE_URL = "https://openapi.naver.com/v1/search/"

        fun create(): NaverAPI {
            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("네이버 통신 로그", it)
            })
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id",
                        BuildConfig.X_Naver_Client_Id
                    )
                    .addHeader("X-Naver-Client-Secret",
                        BuildConfig.X_Naver_Client_Secret
                    )
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverAPI::class.java)
        }
    }

    @GET("movie.json")
    fun getMovie(
        @Query("query") title:String
    ): Single<NaverMovie>
}
