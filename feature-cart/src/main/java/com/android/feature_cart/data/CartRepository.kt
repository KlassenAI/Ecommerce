package com.android.feature_cart.data

import androidx.lifecycle.LiveData
import com.android.core.model.ProductEntity
import com.android.feature_cart.data.db.CartDao
import com.android.feature_cart.data.network.CartService
import com.android.feature_cart.domain.model.Cart
import com.android.feature_cart.domain.repo.ICartRepository

class CartRepository(
    private val service: CartService,
    private val dao: CartDao
): ICartRepository {

    override suspend fun requestCart(): Cart {
        return service.requestCart().firstOrNull() ?: error("Ошибка загрузки данных из сети")
    }

    override fun getProducts(): LiveData<List<ProductEntity>> = dao.getProducts()
}