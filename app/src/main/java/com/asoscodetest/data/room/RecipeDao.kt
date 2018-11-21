package com.asoscodetest.data.room

import android.arch.persistence.room.*
import com.asoscodetest.data.model.Recipe
import io.reactivex.Single

/**
 * Created by David C. on 20/11/2018.
 */
@Dao
interface RecipeDao {

    @Transaction
    fun updateRecipes(recipeList: List<Recipe>) {
        deleteAll()
        insertAll(recipeList)
    }

    @Query("SELECT * FROM recipes")
    fun getRecipes(): Single<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipeList: List<Recipe>)

    @Query("DELETE FROM recipes")
    fun deleteAll()

}