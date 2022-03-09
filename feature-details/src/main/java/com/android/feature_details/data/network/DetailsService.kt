package com.android.feature_details.data.network

import com.android.core.utils.Constants.API_KEY
import com.android.feature_details.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Headers

interface DetailsService {

    @Headers("x-apikey: $API_KEY")
    @GET("/rest/detail")
    suspend fun requestDetails(): List<Product>
}