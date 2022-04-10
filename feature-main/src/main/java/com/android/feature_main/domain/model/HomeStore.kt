package com.android.feature_main.domain.model

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

data class HomeStore(
    var id: Int,
    @SerializedName("is_new")
    @get: PropertyName("is_new") @set: PropertyName("is_new")
    var isNew: Boolean,
    var title: String,
    var subtitle: String,
    var picture: String,
    @SerializedName("is_buy")
    @get: PropertyName("is_buy") @set: PropertyName("is_buy")
    var isBuy: Boolean
) {
    constructor(): this(0, false, "", "", "", false)

    fun toEntity(mainId: String): HomeStoreEntity {
        return HomeStoreEntity(
            mainId,
            id,
            isNew,
            title,
            subtitle,
            picture,
            isBuy
        )
    }
}