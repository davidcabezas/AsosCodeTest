package com.asoscodetest.data.room

import android.arch.persistence.room.*
import com.asoscodetest.data.model.RecipeListExpiration
import io.reactivex.Single

/**
 * Created by David C. on 20/11/2018.
 */
@Dao
interface RecipeListExpirationDao {

    @Transaction
    fun updateRecipesExpiration(recipeExpiration: RecipeListExpiration) {
        deleteAll()
        insert(recipeExpiration)
    }

    @Query("SELECT * FROM recipes_expiration LIMIT 1")
    fun getRecipesExpiration(): Single<RecipeListExpiration>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipeExpiration: RecipeListExpiration): Long

    @Query("DELETE FROM recipes_expiration")
    fun deleteAll()

}