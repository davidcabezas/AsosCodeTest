package com.asoscodetest.retrofit

import com.asoscodetest.data.model.Recipe
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by David C. on 18/11/2018.
 */
interface RecipeApi {

    @GET("recipes.json")
    fun getAllRecipes(): Single<List<Recipe>>

}