package com.asoscodetest.ui.recipelist

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.asoscodetest.R
import com.asoscodetest.data.model.Recipe
import com.asoscodetest.ui.base.BaseActivity
import com.asoscodetest.ui.recipedetail.RecipeDetailActivity
import com.asoscodetest.util.Constants
import com.asoscodetest.util.SharedViewData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_list.*
import javax.inject.Inject

class RecipeListActivity : BaseActivity(), RecipeListView, RecipeListAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: RecipeListPresenter

    @Inject
    lateinit var picasso: Picasso

    private lateinit var recipesAdapter: RecipeListAdapter

    private var firstLoad = 0 // This variable will avoid calling getRecipesData more than one when opening the App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        appComponent.inject(this)

        presenter.attach(this)

        initializeView()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
        presenter.detach()
    }

    private fun initializeView() {

        editText_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) = presenter.applyTextComplexityTimeFilters(text.toString(), spinner_complexity.selectedItemPosition, spinner_time.selectedItemPosition)

        })
        editText_search.text = null // Avoid editText getting the focus and showing soft keyboard when opening the App

        spinner_complexity.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.complexity_filter))
        spinner_complexity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {

                if (firstLoad < 2) {
                    firstLoad++
                } else {
                    presenter.applyTextComplexityTimeFilters(editText_search.text.toString(), index, spinner_time.selectedItemPosition)
                }
            }
        }

        spinner_time.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.time_filter))
        spinner_time.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, index: Int, p3: Long) {

                if (firstLoad < 2) {
                    firstLoad++
                } else {
                    presenter.applyTextComplexityTimeFilters(editText_search.text.toString(), spinner_complexity.selectedItemPosition, index)
                }
            }
        }

        recipesAdapter = RecipeListAdapter(this, picasso)

        recyclerView_items_list.layoutManager = GridLayoutManager(this, 2)
        recyclerView_items_list.adapter = recipesAdapter

    }

    private fun loadRecipeList() = presenter.getAllRecipes()

    override fun showProgress(show: Boolean) {
        loading!!.visibility = if (show) View.VISIBLE else View.GONE
        recyclerView_items_list!!.visibility = if (show) View.GONE else View.VISIBLE
    }

    // Improvement: change system error message by a more friendly one
    override fun showErrorMessage(message: String) =
            notifyWithAction(this.findViewById(R.id.layout_main), message, R.string.retry, ::loadRecipeList)

    override fun printRecipesList(recipeList: List<Recipe>) {

        recipesAdapter.recipeList = recipeList

    }

    override fun onItemClick(item: Recipe, sharedViewData: SharedViewData) = presenter.manageItemClick(item, sharedViewData)

    override fun showRecipeDetailView(recipe: Recipe, sharedViewData: SharedViewData) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra(Constants.RECIPE_OBJECT, recipe)
        val sharedView = sharedViewData.transitionSharedView as ImageView

        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, sharedView, sharedView.transitionName)
        startActivity(intent, activityOptions.toBundle())
    }

}
