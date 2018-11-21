package com.asoscodetest.data.room

import android.arch.persistence.room.TypeConverter
import com.asoscodetest.data.model.Ingredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by David C. on 20/11/2018.
 */
class IngredientsConverters {

    @TypeConverter
    fun fromString(value: String): List<Ingredient> {
        val listType = object : TypeToken<List<Ingredient>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Ingredient>): String {
        return Gson().toJson(list)
    }

}