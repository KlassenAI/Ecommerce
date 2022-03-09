package com.android.ecommerce.data.network

import com.android.core.utils.Constants
import com.android.feature_cart.data.network.CartService
import com.android.feature_details.data.network.DetailsService
import com.android.feature_main.data.network.MainService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    fun getHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    }

    fun getHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    fun getRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun getMainService(builder: Retrofit): MainService = builder.create(MainService::class.java)
    fun getDetailsService(builder: Retrofit): DetailsService = builder.create(DetailsService::class.java)
    fun getCartService(builder: Retrofit): CartService = builder.create(CartService::class.java)
}