package com.asoscodetest.ui.recipelist

import com.asoscodetest.data.model.Recipe
import com.asoscodetest.interactor.get.GetAllRecipesUseCase
import com.asoscodetest.util.SharedViewData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by David C. on 18/11/2018.
 */
class RecipeListPresenterImpl
@Inject constructor(private val getAllRecipesUseCase: GetAllRecipesUseCase) : RecipeListPresenter {

    private var view: RecipeListView? = null
    private val subscriptions = CompositeDisposable()

    override fun subscribe() {
        // Not used in this code test
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: RecipeListView) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun getAllRecipes() {

        view?.showProgress(true)

        val subscribe = getAllRecipesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            view?.showProgress(false)
                            view?.printRecipesList(list)
                        }, { error ->
                    view?.showProgress(false)
                    view?.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)

    }

    override fun applyTextComplexityTimeFilters(text: String, complexity: Int, time: Int) {

        // Complexity will be determined by the number of ingredients of the recipe:
        // 1-5 Easy, 6-10 Medium, 11+ Hard

        view?.showProgress(true)

        // By getting the data from the UseCase every time the user applies a filter we can assure we will get fresh data
        // when the cached data expires
        val subscribe = getAllRecipesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list ->
                            // Apply Text filter
                            val textFilteredList = applyTextFilter(list, text)

                            // Apply Complexity filter over the Text filtered list
                            val complFilteredList = applyComplexityFilter(textFilteredList, complexity)

                            // Apply Time filter over the Text and Complexity filtered list
                            val timeFilteredList = applyTimeFilter(complFilteredList, time)

                            view?.showProgress(false)
                            view?.printRecipesList(timeFilteredList)
                        }, { error ->
                    view?.showProgress(false)
                    view?.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)

    }

    private fun applyTextFilter(list: List<Recipe>, text: String): List<Recipe> {

        return if (text.isNotBlank()) {
            list.filter {
                it.name.contains(text) || it.ingredients.any { it.name.contains(text) } || it.steps.any { it.contains(text) }
            }
        } else {
            list
        }

    }

    private fun applyComplexityFilter(list: List<Recipe>, complexity: Int): List<Recipe> {

        return when (complexity) {
            1 -> list.filter {
                it.ingredients.size < 6
            }
            2 -> list.filter {
                it.ingredients.size in 6..10
            }
            3 -> list.filter {
                it.ingredients.size > 10
            }
            else -> list
        }

    }

    private fun applyTimeFilter(list: List<Recipe>, time: Int): List<Recipe> {

        return when (time) {
            1 -> list.filter {
                it.timers.sum() < 11
            }
            2 -> list.filter {
                it.timers.sum() in 11..20
            }
            3 -> list.filter {
                it.timers.sum() > 20
            }
            else -> list
        }

    }

    override fun manageItemClick(recipe: Recipe, sharedViewData: SharedViewData) {
        view?.showRecipeDetailView(recipe, sharedViewData)
    }

}