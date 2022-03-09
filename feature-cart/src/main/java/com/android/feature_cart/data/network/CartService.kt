package com.android.feature_cart.data.network

import com.android.core.utils.Constants.API_KEY
import com.android.feature_cart.domain.model.Cart
import retrofit2.http.GET
import retrofit2.http.Headers

interface CartService {

    @Headers("x-apikey: $API_KEY")
    @GET("/rest/cart")
    suspend fun requestCart(): List<Cart>
}