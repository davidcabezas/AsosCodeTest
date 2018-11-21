package com.asoscodetest.ui.recipelist

import com.asoscodetest.data.model.Recipe
import com.asoscodetest.ui.base.BasePresenter
import com.asoscodetest.util.SharedViewData

/**
 * Created by David C. on 18/11/2018.
 */
interface RecipeListPresenter : BasePresenter<RecipeListView> {

    fun getAllRecipes()

    fun applyTextComplexityTimeFilters(text: String, complexity: Int, time: Int)

    fun manageItemClick(recipe: Recipe, sharedViewData: SharedViewData)

}