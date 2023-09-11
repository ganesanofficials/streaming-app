package com.example.streamingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.streamingapp.dataModel.ParentList
import com.example.streamingapp.databinding.ItemParentListBinding


class ParentMovieListsAdapter(private var parentList: List<ParentList>): RecyclerView.Adapter<ParentMovieHolder>() {

    private val viewPool = RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentMovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemParentListBinding.inflate(inflater,parent,false)
        return ParentMovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return parentList.size
    }

    override fun onBindViewHolder(holder: ParentMovieHolder, position: Int) {
        var movieList = parentList[position]
        holder.bind(movieList)
    }

    fun setData(parentList: List<ParentList>){
        this.parentList = parentList
    }
}

class ParentMovieHolder(private val binding:ItemParentListBinding):ViewHolder(binding.root){
    private val layoutManager = LinearLayoutManager(
        binding.fragmentHomeRecyclerview
            .context,
        LinearLayoutManager.HORIZONTAL,
        false
    )
    fun bind(parentList: ParentList){
        binding.parentItemTitleLabel.text = parentList.title

        binding.fragmentHomeRecyclerview.also {
            it.layoutManager = layoutManager
            it.adapter = MovieAdapter(parentList.list)
        }
    }

}