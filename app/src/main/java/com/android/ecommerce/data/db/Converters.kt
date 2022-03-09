package com.android.ecommerce.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class GenericConverter<T, V> {

    private val gson = Gson()
    private val type = object : TypeToken<T>() {}.type

    abstract fun convertTo(obj: T?): V?

    abstract fun convertFrom(obj: V?): T?

    fun convertToString(obj: T?): String? = obj?.let { gson.toJson(it, type) }

    fun convertFromString(string: String?): T? = string?.let { gson.fromJson(it, type) }
}

class ListStringConverter : GenericConverter<List<String>, String>() {

    @TypeConverter
    override fun convertTo(obj: List<String>?): String? = convertToString(obj)

    @TypeConverter
    override fun convertFrom(obj: String?): List<String>? = convertFromString(obj)
}