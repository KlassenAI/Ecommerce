package com.android.feature_details.domain.repo

import com.android.feature_details.domain.model.Product
import com.android.core.model.ProductEntity

interface IDetailsRepository {
    suspend fun requestDetailsData(): Product
    suspend fun addProduct(productEntity: ProductEntity)
}