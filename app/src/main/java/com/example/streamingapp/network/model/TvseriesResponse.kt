package com.example.streamingapp.network.model

data class TvSeriesResponse(
    val page: Int,
    val results: List<TvSeries>,
    val total_pages: Int,
    val total_results: Int
)