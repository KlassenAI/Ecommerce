package com.android.feature_main.domain.model

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class HomeData(
    @SerializedName("_id")
    @get: PropertyName("_id") @set: PropertyName("_id")
    var id: String,
    @SerializedName("home_store")
    @get: PropertyName("home_store") @set: PropertyName("home_store")
    var homeStores: List<HomeStore>,
    @SerializedName("best_seller")
    @get: PropertyName("best_seller") @set: PropertyName("best_seller")
    var bestSellers: List<BestSeller>
) {
    constructor(): this("", emptyList(), emptyList())
}