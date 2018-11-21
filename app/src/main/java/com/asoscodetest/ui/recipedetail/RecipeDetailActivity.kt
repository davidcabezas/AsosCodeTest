package com.asoscodetest.ui.recipedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.asoscodetest.R
import com.asoscodetest.data.model.Recipe
import com.asoscodetest.ui.base.BaseActivity
import com.asoscodetest.util.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.row_ingredient.view.*
import kotlinx.android.synthetic.main.row_step.view.*
import javax.inject.Inject

/**
 * Created by David C. on 18/11/2018.
 */
class RecipeDetailActivity : BaseActivity() {

    // As this view only has to show data, I decided not to create the (empty) View's contract,
    // and Presenter's contract and implementation (also empty)

    @Inject
    lateinit var picasso: Picasso

    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        appComponent.inject(this)

        if (intent.hasExtra(Constants.RECIPE_OBJECT)) {
            recipe = intent.getSerializableExtra(Constants.RECIPE_OBJECT) as Recipe

            fillView()
        } else {
            Toast.makeText(this, "An error has occurred", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun fillView() {

        picasso.load(recipe.imageURL).into(imageView_recipe_photo)
        textView_recipe_detail_title.text = recipe.name
        textView_recipe_detail_ingredients.text = "${resources.getString(R.string.ingredients)} (${recipe.ingredients.size})"

        recipe.ingredients.forEach {
            val row = LayoutInflater.from(this).inflate(R.layout.row_ingredient, layout_recipe_ingredients_container, false)
            row.textView_ingredient_name.text = it.name
            row.textView_ingredient_quantity.text = it.quantity

            layout_recipe_ingredients_container.addView(row)
        }

        recipe.steps.forEach {
            val row = LayoutInflater.from(this).inflate(R.layout.row_step, layout_recipe_instructions_container, false)
            row.textView_step_description.text = it

            layout_recipe_instructions_container.addView(row)
        }

    }

}