package com.android.feature_main.domain.model

import com.google.gson.annotations.SerializedName

data class BestSeller(
    val id: Int,
    @SerializedName("is_favorites")
    val isFavorites: Boolean,
    val title: String,
    @SerializedName("price_without_discount")
    val priceWithoutDiscount: Int,
    @SerializedName("discount_price")
    val discountPrice: Int,
    val picture: String
) {
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