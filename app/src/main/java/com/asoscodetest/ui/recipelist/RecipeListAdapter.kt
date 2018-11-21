package com.asoscodetest.ui.recipelist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.asoscodetest.R
import com.asoscodetest.data.model.Recipe
import com.asoscodetest.extension.inflate
import com.asoscodetest.util.SharedViewData
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

/**
 * Created by David C. on 18/11/2018.
 */
class RecipeListAdapter(val listener: OnItemClickListener, val picasso: Picasso) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Recipe, sharedViewData: SharedViewData)
    }

    internal var recipeList: List<Recipe> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(parent.inflate(R.layout.row_recipe_item), picasso)

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) = holder.bind(recipeList[position], listener)

    override fun getItemCount(): Int = recipeList.size

}