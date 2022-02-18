package com.android.feature_main.data.network

import com.android.common.utils.Constants.API_KEY
import com.android.feature_main.domain.model.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitService {

    @Headers("x-apikey: $API_KEY")
    @GET("/rest/home")
    suspend fun requestHome(): Response<List<HomeResponse>>
}