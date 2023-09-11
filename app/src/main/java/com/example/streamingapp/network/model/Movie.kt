package com.example.streamingapp.network.model

import android.os.Parcelable
import com.example.streamingapp.IMAGE_BASE_URL
import com.example.streamingapp.ImageSize
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String = "",
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable

fun Movie.getPosterUrl(size: ImageSize = ImageSize.NORMAL): String {
    return "$IMAGE_BASE_URL${size.value}${this.poster_path}"
}

fun Movie.getBackdropUrl(size: ImageSize = ImageSize.ORIGINAL): String {
    return "$IMAGE_BASE_URL${size.value}${this.poster_path}"
}


