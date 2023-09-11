package com.example.streamingapp.ui.view.videoplayer

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlaybackException
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import com.example.streamingapp.R
import com.example.streamingapp.VideoUrlList
import com.example.streamingapp.base.ViewModelFactory
import com.example.streamingapp.databinding.FragmentVideoPlayerBinding


class VideoPlayerFragment : Fragment() {

    private lateinit var viewModel: VideoPlayerViewModel
    private val args: VideoPlayerFragmentArgs by navArgs()
    lateinit var binding: FragmentVideoPlayerBinding
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_video_player, container, false)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        hideStatusBar()
        getViewModel()
        initUi()
        return binding.root
    }

    private fun hideStatusBar() {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        activity?.actionBar?.hide()
    }

    private fun getViewModel() {
        viewModel = ViewModelProvider(this)[VideoPlayerViewModel::class.java]
    }

    private fun initUi() {
        binding.data = args.movie
        player = ExoPlayer.Builder(requireContext().applicationContext).build()
        binding.styledPlayerView.player = player
        val mediaItem: MediaItem = MediaItem.fromUri(VideoUrlList.getVideoUrl(args.movie.id))
        player.setMediaItem(viewModel.getMediaItem(args.movie.id))
        player.prepare()
        player.playWhenReady = true
//        binding.styledPlayerView.useController = true
        player.seekTo(viewModel.playbackPosition)

    }

    override fun onStop() {
        super.onStop()
        player.playWhenReady = false;
        player.release();
    }

    override fun onPause() {
        super.onPause()
        viewModel.playbackPosition = player.currentPosition
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoPlayerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}