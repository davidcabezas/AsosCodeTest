package com.asoscodetest.di.module

import com.asoscodetest.retrofit.RecipeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by David C. on 22/11/2018.
 */
@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun recipeApi(retrofit: Retrofit): RecipeApi = retrofit.create(RecipeApi::class.java)

}