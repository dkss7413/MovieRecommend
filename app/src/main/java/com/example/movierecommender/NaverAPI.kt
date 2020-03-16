package com.example.movierecommender

import android.util.Log
import com.example.movierecommender.models.MovieModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverAPI {
    companion object {
        val BASE_URL = "https://openapi.naver.com/v1/search/"

        fun create(): NaverAPI{
            val httpLogginInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("통신 로그", it)
            })
            httpLogginInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", BuildConfig.X_Naver_Client_Id)
                    .addHeader("X-Naver-Client-Secret", BuildConfig.X_Naver_Client_Secret)
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(httpLogginInterceptor)
                .addInterceptor(headerInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverAPI::class.java)
        }
    }

    @GET("movie.json")
    fun getMovie(
        @Query("query") title:String
    ): Call<MovieModel>
}
