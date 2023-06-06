package com.zhvk.shoppingkart.model

import androidx.annotation.DrawableRes
import java.text.NumberFormat

/**
 * A data class to represent the information presented in one Product item
 */
data class Product(
    val id: Long,
    val name: String,
    val category: String,
    val type: String,
    val description: String,
    val price: Double,
    val isAvailable: Boolean,
    val isFavourite: Boolean,
    @DrawableRes val imageResourceIds: List<Int>,
    /*@DrawableRes val imageResourceId1: Int,
    @DrawableRes val imageResourceId2: Int?,
    @DrawableRes val imageResourceId3: Int?*/
) {
    // CartItem Class requires empty Product object. Created a secondary constructor instead of defining default values
    constructor() : this(
        -1, "", "", "", "", 0.0, false, false, listOf()
    )

    fun getFormattedPrice(): String {
        return NumberFormat.getCurrencyInstance().format(price)
    }
}

// fun Product.getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
