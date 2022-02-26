package com.android.feature_main.domain.repo

import com.android.feature_main.domain.model.HomeData

interface IHomeRepository {
    suspend fun requestHomeData(): HomeData
    suspend fun getHomeData(mainId: String): HomeData
}