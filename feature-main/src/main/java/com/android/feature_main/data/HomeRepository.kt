package com.android.feature_main.data

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import com.android.core.utils.Constants
import com.android.feature_main.data.db.MainDao
import com.android.feature_main.data.network.MainService
import com.android.feature_main.domain.model.HomeData
import com.android.feature_main.domain.repo.IHomeRepository

class HomeRepository(
    private val service: MainService,
    private val dao: MainDao,
    context: Context
) : IHomeRepository {

    private val settings = context.getSharedPreferences(Constants.SHARED_PREF_SETTINGS, Context.MODE_PRIVATE)

    override suspend fun requestHomeData(): HomeData {
        val mainId = settings.getString(Constants.PREF_KEY_MAIN_ID, null)
        val homeData: HomeData = if (mainId == null) {
            val requestData = service.requestHomeData().firstOrNull() ?: error("Ошибка загрузки данных из сети")
            saveHomeDataFields(requestData)
            requestData
        } else {
            dao.getHomeData(mainId)
        }
        return homeData
    }

    private suspend fun saveHomeDataFields(homeData: HomeData) {
        val homeStoreEntities = homeData.homeStores.map { it.toEntity(homeData.id) }
        val bestSellerEntities = homeData.bestSellers.map { it.toEntity(homeData.id) }
        dao.insertHomeDataFields(homeStoreEntities, bestSellerEntities)
        saveMainId(homeData.id)
    }

    private fun saveMainId(mainId: String) {
        settings.edit {
            putString(Constants.PREF_KEY_MAIN_ID, mainId)
            apply()
        }
    }

    override suspend fun getHomeData(mainId: String): HomeData {
        return dao.getHomeData(mainId)
    }

    override fun getProductCount(): LiveData<Int> = dao.getProductCount()
}