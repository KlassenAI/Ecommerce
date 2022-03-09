package com.android.feature_details.data

import com.android.feature_details.data.db.DetailsDao
import com.android.feature_details.data.network.DetailsService
import com.android.feature_details.domain.model.Product
import com.android.core.model.ProductEntity
import com.android.feature_details.domain.repo.IDetailsRepository

class DetailsRepository(
    private val service: DetailsService,
    private val dao: DetailsDao
) : IDetailsRepository {

    override suspend fun requestDetailsData(): Product {
        return service.requestDetails().firstOrNull() ?: error("Ошибка загрузки данных из сети")
    }

    override suspend fun addProduct(productEntity: ProductEntity) = dao.addProduct(productEntity)
}