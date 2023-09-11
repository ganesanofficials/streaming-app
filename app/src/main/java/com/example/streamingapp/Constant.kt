package com.example.streamingapp

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

object VideoUrlList{
    val list = listOf("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"
    )
    fun getVideoUrl(id:Int):String{
        return list[id % list.size]
    }

}

enum class ImageSize(val value: String){
    NORMAL("w500"),
    ORIGINAL("original"),
}

enum class ItemSize(value: String){
    SMALL("small"),
    LARGE("large")
}

enum class MovieTypes(val value: String,val label:String){
    POPULAR("popular","Popular"),
    TOP_RATED("top_rated","Top Rated"),
    UPCOMING("upcoming","Upcoming"),
    TRENDING("now_playing","Trending"),
    TOP_RATED_TV("top_rated","Top Rated Tv Series"),

}



