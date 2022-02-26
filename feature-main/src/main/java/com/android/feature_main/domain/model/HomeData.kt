package com.android.feature_main.domain.model

import com.google.gson.annotations.SerializedName

data class HomeData(
    @SerializedName("_id")
    val id: String,
    @SerializedName("home_store")
    val homeStores: List<HomeStore>,
    @SerializedName("best_seller")
    val bestSellers: List<BestSeller>
)