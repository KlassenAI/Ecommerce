package com.android.feature_main.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.feature_main.utils.Constants

@Entity(tableName = Constants.DB_TABLE_BEST_SELLERS)
data class BestSellerEntity(
    @ColumnInfo(name = Constants.DB_COLUMN_MAIN_ID)
    val mainId: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "is_favorites")
    val isFavorites: Boolean,
    val title: String,
    @ColumnInfo(name = "price_without_discount")
    val priceWithoutDiscount: Int,
    @ColumnInfo(name = "discount_price")
    val discountPrice: Int,
    val picture: String
) {
    fun toBestSeller(): BestSeller {
        return BestSeller(
            id,
            isFavorites,
            title,
            priceWithoutDiscount,
            discountPrice,
            picture
        )
    }
}