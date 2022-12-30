package com.jhon.data.http

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    // nos da la base de retrofit
    //se estructura toda la url brindada
    //


    val URL = "https://pokeapi.co/api/v2/"
    val interceptors = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = okhttp3.OkHttpClient.Builder().apply {
        this.addInterceptor(interceptors)
    }.build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}