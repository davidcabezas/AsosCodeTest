package com.asoscodetest.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.asoscodetest.data.model.Recipe
import com.asoscodetest.data.model.RecipeListExpiration

/**
 * Created by David C. on 20/11/2018.
 */
@Database(entities = [Recipe::class, RecipeListExpiration::class], version = 2)
@TypeConverters(StepsConverters::class, IngredientsConverters::class, TimersConverters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun recipeListExpirationDao(): RecipeListExpirationDao

}