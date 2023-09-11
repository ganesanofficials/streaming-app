package com.example.streamingapp.network.model

data class TvSeries(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)

fun TvSeries.toMovie():Movie{
    return Movie(poster_path = this.poster_path, title = this.name, backdrop_path = this.backdrop_path,
        genre_ids = this.genre_ids, id = this.id, original_language = this.original_language, original_title = this.original_name,
    overview = this.overview, popularity = this.popularity, release_date = this.first_air_date, video = false, vote_average = this.vote_average,
        vote_count = 0
    )
}

public fun tvSeriesListToMovieList(list: List<TvSeries>): List<Movie> {
    return list.map { it.toMovie() }
}