package com.asoscodetest.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by David C. on 20/11/2018.
 */
@Entity(tableName = "recipes_expiration")
data class RecipeListExpiration(@PrimaryKey val expiration: Long)