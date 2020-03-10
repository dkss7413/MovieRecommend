package com.example.movierecommender

import com.example.movierecommender.models.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    companion object {
        val URL = "https://openapi.naver.com/v1/search/"
    }


    @Headers(
        "X-Naver-Client-Id: " + BuildConfig.X_Naver_Client_Id,
        "X-Naver-Client-Secret: " + BuildConfig.X_Naver_Client_Secret
    )
    @GET("movie.json")
    fun getMovie(
        @Query("query") title:String
    ): Call<MovieModel>
}
