package com.example.streamingapp.ui.view.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamingapp.MovieTypes
import com.example.streamingapp.base.UiState
import com.example.streamingapp.dataModel.ParentList
import com.example.streamingapp.network.api.ApiHelper
import com.example.streamingapp.network.model.Movie
import com.example.streamingapp.network.model.TvSeries
import com.example.streamingapp.network.model.tvSeriesListToMovieList
import com.example.streamingapp.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class HomeViewModel(private val apiHelper: ApiHelper,
                    private val dispatcherProvider: DispatcherProvider
):ViewModel() {

     var popularMovieList = MutableStateFlow<UiState<List<ParentList>>>(UiState.Loading)
    init {
        getPopularMovie()
    }
     fun getPopularMovie(){
        viewModelScope.launch(dispatcherProvider.main) {
            apiHelper.getMoviesList(MovieTypes.POPULAR.value).
                zip(apiHelper.getMoviesList("top_rated")){
                    l1,l2->
                    val mutableList = mutableListOf<ParentList>()
                    mutableList.add(ParentList(MovieTypes.POPULAR.label,l1))
                    mutableList.add(ParentList("Top rated",l2))
                    return@zip mutableList
                }
                .zip(apiHelper.getMoviesList("upcoming")){old,l3->
                    val mutableList = mutableListOf<ParentList>()
                    mutableList.addAll(old)
                    mutableList.add(ParentList("Upcoming",l3))
                    return@zip mutableList
                }.zip(apiHelper.getMoviesList("now_playing")){old,l4->
                    val mutableList = mutableListOf<ParentList>()
                    mutableList.addAll(old)
                    mutableList.add(ParentList("Trending",l4))
                    return@zip mutableList
                }.zip(apiHelper.getTvSeriesList(MovieTypes.TOP_RATED.value)){
                    old,l5->
                    val mutableList = mutableListOf<ParentList>()
                    mutableList.addAll(old)
                    mutableList.add(ParentList(MovieTypes.TOP_RATED_TV.label,tvSeriesListToMovieList(l5)))
                    return@zip mutableList
                }
                .flowOn(dispatcherProvider.io)
                .catch {e->
                    popularMovieList.value = UiState.Error(e.toString())
                }
                .collect(){
                popularMovieList.value =  UiState.Success(it)
            }
        }

    }

}