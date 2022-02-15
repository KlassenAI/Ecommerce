package com.android.ecommerce.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    fun getRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://shopapi-0575.restdb.io")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun getService(builder: Retrofit): RetrofitService = builder.create(RetrofitService::class.java)
}