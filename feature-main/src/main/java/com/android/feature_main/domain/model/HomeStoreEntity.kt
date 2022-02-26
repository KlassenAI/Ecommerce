package com.android.feature_main.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.feature_main.utils.Constants

@Entity(tableName = Constants.DB_TABLE_HOME_STORES)
data class HomeStoreEntity(
    @ColumnInfo(name = Constants.DB_COLUMN_MAIN_ID)
    val mainId: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "is_new")
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
    @ColumnInfo(name = "is_buy")
    val isBuy: Boolean
) {
    fun toHomeStore(): HomeStore {
        return HomeStore(
            id,
            isNew,
            title,
            subtitle,
            picture,
            isBuy
        )
    }
}