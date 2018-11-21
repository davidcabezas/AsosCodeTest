package com.asoscodetest.di

import com.asoscodetest.AsosApplication
import com.asoscodetest.di.module.AppModule
import com.asoscodetest.di.module.DatabaseModule
import com.asoscodetest.di.module.NetworkModule
import com.asoscodetest.di.module.PicassoModule
import com.asoscodetest.ui.recipedetail.RecipeDetailActivity
import com.asoscodetest.ui.recipelist.RecipeListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by David C. on 18/11/2018.
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class, PicassoModule::class])
interface AppComponent {

    fun inject(app: AsosApplication)
    fun inject(recipeListActivity: RecipeListActivity)
    fun inject(recipeDetailActivity: RecipeDetailActivity)

}