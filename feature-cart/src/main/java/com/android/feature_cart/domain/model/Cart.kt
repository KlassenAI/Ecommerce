package com.android.feature_cart.domain.model

import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("_id")
    val id: String,
    val delivery: String,
    val total: Int,
    val basket: List<BasketItem>
)