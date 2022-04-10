package com.android.feature_main.domain.model

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class BestSeller(
    var id: Int,
    @SerializedName("is_favorites")
    @get: PropertyName("is_favorites") @set: PropertyName("is_favorites")
    var isFavorites: Boolean,
    var title: String,
    @SerializedName("price_without_discount")
    @get: PropertyName("price_without_discount") @set: PropertyName("price_without_discount")
    var priceWithoutDiscount: Int,
    @SerializedName("discount_price")
    @get: PropertyName("discount_price") @set: PropertyName("discount_price")
    var discountPrice: Int,
    var picture: String
) {
    constructor(): this(0, false, "", 0, 0, "")

    fun toEntity(mainId: String): BestSellerEntity {
        return BestSellerEntity(
            mainId,
            id,
            isFavorites,
            title,
            priceWithoutDiscount,
            discountPrice,
            picture
        )
    }
}