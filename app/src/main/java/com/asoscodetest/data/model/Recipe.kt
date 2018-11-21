package com.asoscodetest.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 * Created by David C. on 17/11/2018.
 */
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val timers: List<Int>,
    val imageURL: String,
    val originalURL: String?
) : Serializable