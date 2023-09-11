package com.example.streamingapp.network.api

import com.example.streamingapp.network.model.Movie
import com.example.streamingapp.network.model.TvSeries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService):ApiHelper {
    override suspend fun getMoviesList(type:String): Flow<List<Movie>> {
        return flow {
            emit (apiService.getMovieList(type).body()!!.results)
        }
    }

    override suspend fun getTvSeriesList(type: String): Flow<List<TvSeries>> {
        return flow {
            emit(apiService.getTvSeriesList(type).body()!!.results)
        }
    }

    override suspend fun getPersonMovieList(): Flow<List<Movie>> {
       return flow {
           emit(apiService.getPersonMovieList().body()!!.results)
       }
    }
}