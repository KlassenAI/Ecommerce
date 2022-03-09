package com.android.feature_details.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.android.core.model.ProductEntity

@Dao
interface DetailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(productEntity: ProductEntity)
}