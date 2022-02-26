package com.android.feature_main.data.db

import androidx.room.*
import com.android.feature_main.domain.model.BestSellerEntity
import com.android.feature_main.domain.model.HomeData
import com.android.feature_main.domain.model.HomeStoreEntity
import com.android.feature_main.utils.Constants

@Dao
interface MainDao {

    @Transaction
    suspend fun getHomeData(mainId: String): HomeData {
        val homeStoreEntities = getHomeStoreEntities(mainId)
        val bestSellerEntities = getBestSellerEntities(mainId)
        val homeStores = homeStoreEntities.map { it.toHomeStore() }.toList()
        val bestSellers = bestSellerEntities.map { it.toBestSeller() }.toList()
        return HomeData(mainId, homeStores , bestSellers)
    }

    @Query("SELECT * FROM ${Constants.DB_TABLE_HOME_STORES} WHERE ${Constants.DB_COLUMN_MAIN_ID} = :id")
    suspend fun getHomeStoreEntities(id: String): List<HomeStoreEntity>

    @Query("SELECT * FROM ${Constants.DB_TABLE_BEST_SELLERS} WHERE ${Constants.DB_COLUMN_MAIN_ID} = :id")
    suspend fun getBestSellerEntities(id: String): List<BestSellerEntity>

    @Transaction
    suspend fun insertHomeDataFields(homeStores: List<HomeStoreEntity>, bestSellers: List<BestSellerEntity>) {
        deleteAllHomeStores()
        addHomeStores(homeStores)
        deleteAllBestSellers()
        addBestSellers(bestSellers)
    }

    @Query("DELETE FROM ${Constants.DB_TABLE_HOME_STORES}")
    suspend fun deleteAllHomeStores()

    @Query("DELETE FROM ${Constants.DB_TABLE_BEST_SELLERS}")
    suspend fun deleteAllBestSellers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHomeStores(homeStores: List<HomeStoreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBestSellers(bestSellers: List<BestSellerEntity>)
}