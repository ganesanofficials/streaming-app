package com.example.streamingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.streamingapp.R
import com.example.streamingapp.databinding.ItemSmallMovieListLayoutBinding

import com.example.streamingapp.network.model.Movie
import com.example.streamingapp.network.model.getPosterUrl
import com.example.streamingapp.ui.view.homescreen.HomeFragmentDirections
import com.example.streamingapp.ui.view.videoplayer.VideoPlayerFragmentArgs
import com.example.streamingapp.ui.view.videoplayer.VideoPlayerFragmentDirections


class MovieAdapter(private var list: List<Movie>):RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSmallMovieListLayoutBinding.inflate(inflater,parent,false)
        return MovieViewHolder(binding)
    }

    fun setData(list: List<Movie>){
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       val movie = list[position]
       holder.bind(movie)

    }
}
class MovieViewHolder(private val binding: ItemSmallMovieListLayoutBinding) : ViewHolder(binding.root){
    fun bind(movie: Movie){
        Glide.with(binding.itemSmallMovieImage)
            .load(movie.getPosterUrl())
            .transform(CenterCrop())
            .into(binding.itemSmallMovieImage)
        binding.itemSmallMovieImage.clipToOutline = true
        binding.itemSmallMovieImage.setOnClickListener {
          val action = HomeFragmentDirections.actionHomeFragmentToVideoPlayerFragment(movie)
            itemView.findNavController().navigate(action)
        }
    }

}