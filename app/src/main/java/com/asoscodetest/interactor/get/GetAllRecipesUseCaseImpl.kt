package com.asoscodetest.interactor.get

import com.asoscodetest.data.model.Recipe
import com.asoscodetest.repository.RecipeRepository
import io.reactivex.Single

/**
 * Created by David C. on 18/11/2018.
 */
class GetAllRecipesUseCaseImpl(private val recipesRepository: RecipeRepository) : GetAllRecipesUseCase {

    override fun execute(): Single<List<Recipe>> = recipesRepository.getAllRecipes()

}