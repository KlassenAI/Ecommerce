package com.android.feature_main.domain.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("home_store")
    val homeStore: List<HomeStore>,
    @SerializedName("best_seller")
    val bestSeller: List<BestSeller>
)