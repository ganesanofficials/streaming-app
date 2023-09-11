package com.example.streamingapp.network.api


import com.example.streamingapp.network.model.MovieResponse
import com.example.streamingapp.network.model.TvSeriesResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/{type}")
    suspend fun getMovieList(@Path("type") type: String): Response<MovieResponse>

    @GET("tv/{type}")
    suspend fun getTvSeriesList(@Path("type") type: String): Response<TvSeriesResponse>

    @GET("person/popular")
    suspend fun getPersonMovieList():Response<MovieResponse>

}