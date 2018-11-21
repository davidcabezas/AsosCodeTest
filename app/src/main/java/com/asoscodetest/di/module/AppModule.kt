package com.asoscodetest.di.module

import com.asoscodetest.interactor.get.GetAllRecipesUseCase
import com.asoscodetest.ui.recipelist.RecipeListPresenter
import com.asoscodetest.ui.recipelist.RecipeListPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by David C. on 18/11/2018.
 */
@Module(includes = [UseCaseModule::class])
class AppModule() {

    @Provides
    @Singleton
    fun recipeListPresenter(getAllRecipesUseCase: GetAllRecipesUseCase): RecipeListPresenter =
        RecipeListPresenterImpl(getAllRecipesUseCase)

}