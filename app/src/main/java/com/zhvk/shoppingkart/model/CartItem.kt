package com.zhvk.shoppingkart.model

data class CartItem (
    val product: Product = Product(-1,"","",",",-10.0,false,  0),
    var quantity: Int = 0
)
