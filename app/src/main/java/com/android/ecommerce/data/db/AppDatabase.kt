package com.android.ecommerce.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.core.utils.Constants
import com.android.core.model.ProductEntity
import com.android.feature_cart.data.db.CartDao
import com.android.feature_details.data.db.DetailsDao
import com.android.feature_main.data.db.HomeDao
import com.android.feature_main.domain.model.BestSellerEntity
import com.android.feature_main.domain.model.HomeStoreEntity

@Database(
    entities = [
        BestSellerEntity::class,
        HomeStoreEntity::class,
        ProductEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ListStringConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mainDao(): HomeDao
    abstract fun detailsDao(): DetailsDao
    abstract fun cartDao(): CartDao

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java, Constants.DB_NAME
            ).build()
        }

        fun getMainDao(database: AppDatabase): HomeDao = database.mainDao()
        fun getDetailsDao(database: AppDatabase): DetailsDao = database.detailsDao()
        fun getCartDao(database: AppDatabase): CartDao = database.cartDao()
    }
}