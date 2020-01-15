package com.ironfake.ageofempires2wiki.api

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TypeConverters {

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

/*    @TypeConverter
    fun stringToList(value: String?):  {
        val mapType = object : TypeToken<HashMap<String?, Int?>?>() {}.type
        return Gson().fromJson<HashMap<String?, Int?>>(value, mapType)
    }

    @TypeConverter
    fun fromStringMap(map: HashMap<String?, Int?>?): String? {
        val gson = Gson()
        return gson.toJson(map)
    }*/

    @TypeConverter
    fun fromArrayList(list: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}