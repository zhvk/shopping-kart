package com.zhvk.shoppingkart.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

private const val SHIPPING_PRICE_PER_ITEM = 25.00

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>()
    val cartItems: LiveData<MutableList<CartItem>> get() = _cartItems

    private val _subtotalPrice = MutableLiveData<Double>()
    val subtotalPrice: LiveData<String> = Transformations.map(_subtotalPrice) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _shippingPrice = MutableLiveData<Double>()
    val shippingPrice: LiveData<String> = Transformations.map(_shippingPrice) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<String> = Transformations.map(_totalPrice) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        resetOrder()
    }

    fun addItem(cartItem: CartItem) {

        val searchedItem = _cartItems.value?.singleOrNull { it.product.id == cartItem.product.id }

        if (searchedItem != null)
            searchedItem.quantity++
        else
            _cartItems.value?.add(cartItem)


        updatePrice()
    }

    fun removeItem(cartItem: CartItem) {
        val searchedItem = _cartItems.value?.singleOrNull { it.product.id == cartItem.product.id }
        if (searchedItem != null) {
            if (searchedItem.quantity > 0)
                searchedItem.quantity--
            else
                _cartItems.value?.remove(cartItem)
        }

        updatePrice()
    }

    fun getCartSize(): Int {
        var cartSize = 0

        for (item in _cartItems.value!!) {
            cartSize += item.quantity
        }

        return cartSize
    }

    private fun resetOrder() {
        _cartItems.value = mutableListOf<CartItem>()
        _subtotalPrice.value = 0.0
        _shippingPrice.value = 0.0
        _totalPrice.value = 0.0
    }

    private fun updatePrice() {
        var calculatedSubtotalPrice = 0.0
        var calculatedShippingPrice = 0.0

        for (item in _cartItems.value!!) {
            calculatedSubtotalPrice += item.product.price * item.quantity
            calculatedShippingPrice += SHIPPING_PRICE_PER_ITEM * item.quantity
        }

        _subtotalPrice.value = calculatedSubtotalPrice
        _shippingPrice.value = calculatedShippingPrice
        _totalPrice.value = calculatedSubtotalPrice + calculatedShippingPrice
    }

    private fun alreadyInCart(product: Product): Int {
        return -1 // or return position where the item is located
    }
}