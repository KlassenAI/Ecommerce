package com.android.feature_main.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import com.android.core.utils.Constants
import com.android.feature_main.data.db.HomeDao
import com.android.feature_main.data.network.HomeService
import com.android.feature_main.domain.model.HomeData
import com.android.feature_main.domain.repo.IHomeRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeRepository(
    private val service: HomeService,
    private val dao: HomeDao,
    context: Context
) : IHomeRepository {

    val settings = context.getSharedPreferences(Constants.SHARED_PREF_SETTINGS, Context.MODE_PRIVATE)

    override suspend fun requestHomeData(): HomeData {
        val mainId = settings.getString(Constants.PREF_KEY_MAIN_ID, null)
        val homeData: HomeData = if (mainId == null) {
            val requestData = service.requestHomeData().firstOrNull() ?: error("Ошибка загрузки данных из сети")
            saveHomeDataFields(Constants.PREF_KEY_MAIN_ID, requestData)
            requestData
        } else {
            dao.getHomeData(mainId)
        }
        return homeData
    }

    suspend fun saveHomeDataFields(key: String, homeData: HomeData) {
        val homeStoreEntities = homeData.homeStores.map { it.toEntity(homeData.id) }
        val bestSellerEntities = homeData.bestSellers.map { it.toEntity(homeData.id) }
        dao.insertHomeDataFields(homeStoreEntities, bestSellerEntities)
        saveMainId(key, homeData.id)
    }

    private fun saveMainId(key: String, mainId: String) {
        settings.edit {
            putString(key, mainId)
            apply()
        }
    }

    override suspend fun getHomeData(mainId: String): HomeData {
        return dao.getHomeData(mainId)
    }

    override fun getProductCount(): LiveData<Int> = dao.getProductCount()
}