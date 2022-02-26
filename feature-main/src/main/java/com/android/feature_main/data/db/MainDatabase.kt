package com.android.feature_main.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.feature_main.domain.model.BestSellerEntity
import com.android.feature_main.domain.model.HomeStoreEntity
import com.android.feature_main.utils.Constants

@Database(
    entities = [BestSellerEntity::class, HomeStoreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun dao(): MainDao

    companion object {
        fun getDatabase(context: Context): MainDatabase {
            return Room.databaseBuilder(
                context, MainDatabase::class.java, Constants.DB_NAME
            ).build()
        }

        fun getDao(database: MainDatabase): MainDao = database.dao()
    }
}