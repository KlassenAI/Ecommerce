package com.android.ecommerce.data.network

import com.android.ecommerce.domain.model.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

private const val API_KEY = "61ddae2e95cb716ea5ee48e4"

interface RetrofitService {

    @Headers("x-apikey: $API_KEY")
    @GET("/rest/home")
    suspend fun requestHome(): Response<List<HomeResponse>>
}