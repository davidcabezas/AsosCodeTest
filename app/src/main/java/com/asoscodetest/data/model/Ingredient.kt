package com.asoscodetest.data.model

import java.io.Serializable

/**
 * Created by David C. on 17/11/2018.
 */
data class Ingredient(
    val quantity: String,
    val name: String,
    val type: String
) : Serializable