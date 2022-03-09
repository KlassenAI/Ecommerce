package com.android.feature_cart.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.core.model.ProductEntity
import com.android.core.utils.Constants

@Dao
interface CartDao {

    @Query("SELECT * FROM ${Constants.DB_TABLE_PRODUCTS}")
    fun getProducts(): LiveData<List<ProductEntity>>
}