package com.asoscodetest.repository

import android.util.Log
import com.asoscodetest.data.model.Recipe
import com.asoscodetest.data.model.RecipeListExpiration
import com.asoscodetest.data.room.RecipeDao
import com.asoscodetest.data.room.RecipeListExpirationDao
import com.asoscodetest.retrofit.RecipeApi
import com.asoscodetest.util.Constants
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by David C. on 18/11/2018.
 */
class RecipeRepository
@Inject
constructor(val recipeApi: RecipeApi, recipeDao: RecipeDao, recipeListExpirationDao: RecipeListExpirationDao) : RecipeApi {

    private val TAG = javaClass.simpleName

    private val cacheRecipeDao = recipeDao
    private val expirationRecipeDao = recipeListExpirationDao

    override fun getAllRecipes(): Single<List<Recipe>> {

        return expirationRecipeDao.getRecipesExpiration()
                .flatMap { expirationTime ->

                    val currentTime = System.currentTimeMillis()

                    if (currentTime < expirationTime.expiration) {  // Check if current time is lower than expiration time

                        Log.d(TAG, "Fetch data from Cache")
                        return@flatMap cacheRecipeDao.getRecipes()

                    } else {

                        Log.d(TAG, "Fetch data from API")
                        return@flatMap recipeApi.getAllRecipes()
                                .subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io())
                                .doOnSuccess { recipeList ->

                                    // Once we fetch the data from the API, store it in the cache and store also its
                                    // expiration time

                                    cacheRecipeDao.updateRecipes(recipeList)

                                    val currentTime = System.currentTimeMillis()
                                    val expiration = currentTime + (Constants.CACHE_DURATION)
                                    expirationRecipeDao.updateRecipesExpiration(RecipeListExpiration(expiration))

                                }

                    }
                }

    }

}