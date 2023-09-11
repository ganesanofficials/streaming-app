package com.example.streamingapp.network.api

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        @SuppressLint("SuspiciousIndentation")
        private fun getClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()

                builder.addInterceptor { chain ->
                    var request = chain.request()
                    val url = request.url.newBuilder().addQueryParameter("api_key",
                        "c1c09e7cbaf70bc4dd30b5c39c928b95").build()
                    request = request.newBuilder().url(url).build()
                    chain.proceed(request)
                }
            return builder.build()

        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }




}