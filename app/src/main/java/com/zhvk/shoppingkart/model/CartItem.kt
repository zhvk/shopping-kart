package com.zhvk.shoppingkart.model

data class CartItem (
    val product: Product = Product(),
    var quantity: Int = 0
)
