package com.zhvk.shoppingkart.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat

private const val SHIPPING_PRICE_PER_ITEM = 25.00
private const val TAG = "CartViewModel"

/**
 * Shared ViewModel that handles all Cart business logic
 */
class CartViewModel : ViewModel(), CartItemListener {

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

    private val _address = MutableLiveData<UserAddress>()
    val address: LiveData<UserAddress> get() = _address

    init {
        resetOrder()
    }

    override fun onReduceQuantityClicked(cartItem: CartItem) {
        reduceItemQuantity(cartItem)
    }

    override fun onIncreaseQuantityClicked(cartItem: CartItem) {
        addItem(cartItem)
    }

    override fun onDeleteItemClicked(cartItem: CartItem) {
        removeItemFromCart(cartItem)
    }

    fun getItemQuantity(cartItem: CartItem): Int {
        val searchedItem = _cartItems.value?.singleOrNull { it.product.id == cartItem.product.id }
        return searchedItem?.quantity ?: 0
    }

    fun getCartSize(): Int {
        var cartSize = 0

        for (item in _cartItems.value!!) {
            cartSize += item.quantity
        }

        return cartSize
    }

    fun setAddress(address: UserAddress) {
        _address.value = address
    }

    fun addItem(cartItem: CartItem) {
        val searchedItem = _cartItems.value?.singleOrNull { it.product.id == cartItem.product.id }

        if (searchedItem != null)
            searchedItem.quantity++
        else
            _cartItems.value?.add(cartItem)

        updatePrice()
    }

    fun reduceItemQuantity(cartItem: CartItem) {
        val searchedItem = _cartItems.value?.singleOrNull { it.product.id == cartItem.product.id }

        if (searchedItem != null) {
            if (searchedItem.quantity > 0)
                searchedItem.quantity--
            else
                _cartItems.value?.remove(cartItem)
        }

        updatePrice()
    }

    private fun removeItemFromCart(cartItem: CartItem) {
        _cartItems.value?.remove(cartItem)
        updatePrice()
    }

    private fun resetOrder() {
        _cartItems.value = mutableListOf<CartItem>()
        _subtotalPrice.value = 0.0
        _shippingPrice.value = 0.0
        _totalPrice.value = 0.0
        _address.value = UserAddress(
            "George Washington St.", "19A", "91732", "Dry Creek"
        )
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

        Log.d(
            TAG, "Updated price: \n subtotalPrice: ${_subtotalPrice.value} \n shippingPrice: " +
                    "${_shippingPrice.value} \n totalPrice: ${_totalPrice.value}"
        )
    }

    private fun alreadyInCart(product: Product): Int {
        return -1 // or return position where the item is located
    }
}