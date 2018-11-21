package com.asoscodetest.di.module

import android.content.Context
import com.asoscodetest.data.room.AppDatabase
import com.asoscodetest.data.room.RecipeDao
import com.asoscodetest.data.room.RecipeListExpirationDao
import com.huma.room_for_asset.RoomAsset
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David C. on 20/11/2018.
 */
@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides
    @Singleton
    fun recipeListExpirationDao(database: AppDatabase) : RecipeListExpirationDao = database.recipeListExpirationDao()

    @Provides
    @Singleton
    fun recipeDao(database: AppDatabase) : RecipeDao = database.recipeDao()

    @Provides
    @Singleton
    fun database(context: Context): AppDatabase = RoomAsset.databaseBuilder(
        context,
        AppDatabase::class.java, "asoscodetest.db"
    ).build()
    // Used RoomAsset to load a pre populated database with just a default RecipeListExpiration value
    // to avoid App's first run error

}