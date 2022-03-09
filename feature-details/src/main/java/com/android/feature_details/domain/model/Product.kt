package com.android.feature_details.domain.model

import com.android.core.model.ProductEntity
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val id: String,
    @SerializedName("CPU")
    val cpu: String,
    val camera: String,
    @SerializedName("isFavorites")
    val favorite: Boolean,
    val price: Int,
    val rating: Int,
    val sd: String,
    val ssd: String,
    val title: String,
    val images: List<String>,
    val color: List<String>,
    val capacity: List<String>
) {
    fun toEntity(color: String, capacity: String): ProductEntity {
        return ProductEntity(0,
            id, cpu, camera, favorite, price, rating, sd, ssd, title, images, color, capacity
        )
    }
}