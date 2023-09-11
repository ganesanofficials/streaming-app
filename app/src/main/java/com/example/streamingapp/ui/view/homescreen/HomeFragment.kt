package com.example.streamingapp. ui.view.homescreen

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamingapp.R
import com.example.streamingapp.adapter.MovieAdapter
import com.example.streamingapp.adapter.ParentMovieListsAdapter
import com.example.streamingapp.databinding.FragmentHomeBinding
import com.example.streamingapp.base.UiState
import com.example.streamingapp.base.ViewModelFactory
import com.example.streamingapp.dataModel.ParentList
import com.example.streamingapp.network.api.ApiClient
import com.example.streamingapp.network.api.ApiHelperImpl
import com.example.streamingapp.network.model.Movie
import com.example.streamingapp.utils.DefaultDispatcherProvider
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var viewmodel:HomeViewModel
    lateinit var adapter : ParentMovieListsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        activity?.requestedOrientation  = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getViewModel()
        initData()
        return binding.root
    }

    private fun initAdapter(adapter: ParentMovieListsAdapter) {
        binding.fragmentHomeRecyclerview.also {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapter
        }
    }

    private fun getViewModel() {
        viewmodel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(ApiClient.retrofit),
                DefaultDispatcherProvider()
            )
        )[HomeViewModel::class.java]
    }

    private fun initData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.popularMovieList.collect() {
                    it.apply {
                        when (it) {
                            is UiState.Success -> {
                                binding.fragmentHomeProgressbar.visibility = View.GONE
                                renderList(it.data)
                            }

                            is UiState.Loading -> {
                                binding.fragmentHomeProgressbar.visibility = View.VISIBLE
                            }

                            is UiState.Error -> {

                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(movies: List<ParentList>) {
        Log.d("MOVIES",movies.toString())
       initAdapter(ParentMovieListsAdapter(movies))
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}