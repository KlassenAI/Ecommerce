package com.android.feature_cart.domain.repo

import androidx.lifecycle.LiveData
import com.android.core.model.ProductEntity
import com.android.feature_cart.domain.model.Cart

interface ICartRepository {
    fun getProducts(): LiveData<List<ProductEntity>>
    suspend fun requestCart(): Cart
}