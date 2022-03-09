package com.android.feature_cart.domain.model

import com.google.gson.annotations.SerializedName

data class BasketItem(
    val id: Int,
    @SerializedName("images")
    val image: String,
    val price: Int,
    val title: String
)