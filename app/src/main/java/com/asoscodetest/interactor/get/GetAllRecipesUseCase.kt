package com.asoscodetest.interactor.get

import com.asoscodetest.data.model.Recipe
import io.reactivex.Single

/**
 * Created by David C. on 18/11/2018.
 */
interface GetAllRecipesUseCase {

    fun execute(): Single<List<Recipe>>

}