package com.example.streamingapp.ui.view.videoplayer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import com.example.streamingapp.VideoUrlList


class VideoPlayerViewModel():ViewModel() {
    lateinit var mediaItem: MediaItem
    var playbackPosition: Long = 0

    fun getMediaItem(id:Int): MediaItem {
        return if(::mediaItem.isInitialized){
            mediaItem
        }else{
            mediaItem = MediaItem.fromUri(VideoUrlList.getVideoUrl(id))
            mediaItem
        }
    }
}