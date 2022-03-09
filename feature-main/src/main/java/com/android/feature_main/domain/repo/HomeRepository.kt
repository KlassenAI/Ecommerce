package com.android.feature_main.domain.repo

import androidx.lifecycle.LiveData
import com.android.feature_main.domain.model.HomeData

interface IHomeRepository {
    suspend fun requestHomeData(): HomeData
    suspend fun getHomeData(mainId: String): HomeData
    fun getProductCount(): LiveData<Int>
}