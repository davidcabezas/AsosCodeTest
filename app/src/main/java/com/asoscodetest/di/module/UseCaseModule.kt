package com.asoscodetest.di.module

import com.asoscodetest.interactor.get.GetAllRecipesUseCase
import com.asoscodetest.interactor.get.GetAllRecipesUseCaseImpl
import com.asoscodetest.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David C. on 18/11/2018.
 */
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun getAllRecipesUseCase(recipeRepository: RecipeRepository): GetAllRecipesUseCase =
        GetAllRecipesUseCaseImpl(recipeRepository)

}