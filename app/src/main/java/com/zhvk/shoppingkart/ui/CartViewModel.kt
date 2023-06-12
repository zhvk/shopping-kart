package com.zhvk.shoppingkart.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zhvk.shoppingkart.model.CartItem
import com.zhvk.shoppingkart.model.Product
import com.zhvk.shoppingkart.model.UserAddress
import com.zhvk.shoppingkart.model.data.DataSource
import java.text.NumberFormat

private const val TAG = "CartViewModel"

/**
 * Shared ViewModel that handles all Cart business logic
 */
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

    private val _address = MutableLiveData<UserAddress>()
    val address: LiveData<UserAddress> get() = _address

    init {
        resetOrder()
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

    fun getProduct(productId: Long) = DataSource.products.firstOrNull { it.id == productId }

    fun getBrowseData(): List<Product> {
        return DataSource.products.shuffled()
    }

    fun getFavourites(): List<Product> {
        return DataSource.products.filter { it.isFavourite }
    }

    fun checkoutButtonVisibility(): Int {
        if (_cartItems.value?.size != 0) return View.VISIBLE
        return View.GONE
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

    fun removeItemFromCart(cartItem: CartItem) {
        _cartItems.value?.remove(cartItem)
        updatePrice()

        Log.d(TAG, "_totalPrice: $_totalPrice")
    }

    fun getOrderString(): String {
        val sb = StringBuilder()
        for (item in cartItems.value!!) {
            sb.append("* Product: ${item.product.name}, quantity: ${item.quantity}, price: $${item.product.price}\n")
        }
        sb.append("---\n").append("Total price: ${totalPrice.value}")
        return sb.toString()
    }

    private fun resetOrder() {
        _cartItems.value = mutableListOf<CartItem>()
        _subtotalPrice.value = 0.0
        _shippingPrice.value = 0.0
        _totalPrice.value = 0.0
        _address.value = UserAddress(
            "George Washington St.", "19A", "91732", "Dry Creek"
        )

        // TODO: Remove this
        addRandomItems(5)
    }

    private fun updatePrice() {
        var calculatedSubtotalPrice = 0.0
        var calculatedShippingPrice = 0.0

        for (item in _cartItems.value!!) {
            calculatedSubtotalPrice += item.product.price * item.quantity
            // TODO: Calculate shipping price based on some data from the product (e.g. weight)
            calculatedShippingPrice += item.product.price * 0.25 * item.quantity
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

    private fun addRandomItems(times: Int) {
        repeat(times) {
            val product = DataSource.products.firstOrNull { it.id == (1..21).random().toLong() }
            if (product != null) addItem(CartItem(product, (1..3).random()))
        }
    }
}