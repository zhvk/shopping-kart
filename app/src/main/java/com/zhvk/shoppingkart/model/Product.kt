package com.zhvk.shoppingkart.model

import androidx.annotation.DrawableRes
import java.text.NumberFormat

/**
 * A data class to represent the information presented in one Product item
 */
data class Product(
    val id: Int,
    val name: String,
    val type: String,
    val description: String,
    val price: Double,
    val isAvailable: Boolean,
    @DrawableRes val imageResourceIds: List<Int>,
    /*@DrawableRes val imageResourceId1: Int,
    @DrawableRes val imageResourceId2: Int?,
    @DrawableRes val imageResourceId3: Int?*/
) {
    fun getFormattedPrice(): String {
        return NumberFormat.getCurrencyInstance().format(price)
    }
}
