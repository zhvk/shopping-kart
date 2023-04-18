package com.zhvk.shoppingkart.model

/**
 * Interface implemented by CartViewModel in order to handle SummaryFragment Cart ViewHolder logic
 */
interface CartItemListener {
    fun onReduceQuantityClicked(cartItem: CartItem)
    fun onIncreaseQuantityClicked(cartItem: CartItem)
    fun onDeleteItemClicked(cartItem: CartItem)
}