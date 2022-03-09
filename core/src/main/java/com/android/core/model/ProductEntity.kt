package com.android.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.core.utils.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.DB_TABLE_PRODUCTS)
data class ProductEntity(
    @ColumnInfo(name = Constants.DB_COLUMN_DETAILS_ID)
    @PrimaryKey(autoGenerate = true)
    val detailsId: Int,
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
    val color: String,
    val capacity: String,
    var number: Int = 1
)