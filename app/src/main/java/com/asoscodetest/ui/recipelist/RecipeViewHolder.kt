package com.asoscodetest.ui.recipelist

import android.support.v7.widget.RecyclerView
import android.view.View
import com.asoscodetest.R
import com.asoscodetest.data.model.Recipe
import com.asoscodetest.util.SharedViewData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_recipe_item.view.*

/**
 * Created by David C. on 18/11/2018.
 */
class RecipeViewHolder(itemView: View, val picasso: Picasso) : RecyclerView.ViewHolder(itemView) {

    fun bind(recipe: Recipe, clickListener: RecipeListAdapter.OnItemClickListener) {
        picasso.load(recipe.imageURL).into(itemView.imageView_recipe_photo)
        itemView.textView_recipe_row_title.text = recipe.name
        itemView.textView_recipe_row_ingredients.text =
                "${recipe.ingredients.size} ${itemView.resources.getString(R.string.ingredients)}"
        itemView.textView_recipe_row_minutes.text =
                "${recipe.timers.sum()} ${itemView.resources.getString(R.string.minutes)}"
        itemView.setOnClickListener { clickListener.onItemClick(recipe, SharedViewData(itemView.imageView_recipe_photo)) }
    }

}