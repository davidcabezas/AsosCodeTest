package com.asoscodetest.ui.recipelist

import com.asoscodetest.data.model.Recipe
import com.asoscodetest.ui.base.BaseView
import com.asoscodetest.util.SharedViewData

/**
 * Created by David C. on 18/11/2018.
 */
interface RecipeListView : BaseView {

    fun printRecipesList(recipeList: List<Recipe>)

    fun showRecipeDetailView(recipe: Recipe, sharedViewData: SharedViewData)

}