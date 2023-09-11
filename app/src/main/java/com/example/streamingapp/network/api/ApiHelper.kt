package com.example.streamingapp.network.api

import com.example.streamingapp.network.model.Movie
import com.example.streamingapp.network.model.TvSeries
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getMoviesList(string: String): Flow<List<Movie>>
    suspend fun getTvSeriesList(string: String): Flow<List<TvSeries>>
    suspend fun getPersonMovieList():Flow<List<Movie>>
}