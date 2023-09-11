package com.example.streamingapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streamingapp.network.api.ApiHelper
import com.example.streamingapp.ui.view.homescreen.HomeViewModel
import com.example.streamingapp.utils.DispatcherProvider

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(apiHelper, dispatcherProvider) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}