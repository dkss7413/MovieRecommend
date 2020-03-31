package com.example.movierecommender.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Service {
    companion object{
        val BASE_URL = "http://dkss852.dothome.co.kr/movieRecommend/"

        fun create(): Service{
            val httpLoggingInterceptor = HttpLoggingInterceptor (HttpLoggingInterceptor.Logger {
                Log.d("서버 로그", it)
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
                .create(Service::class.java)
        }
    }

    @FormUrlEncoded
    @POST("user_register.php")
    fun register(
        @Field("userId") userId: String,
        @Field("userPassword") userPassword: String,
        @Field("nickname") nickName: String
    ): Single<JsonObject>
}