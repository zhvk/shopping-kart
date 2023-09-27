package com.zhvk.shoppingkart.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zhvk.shoppingkart.data.AppRepository
import com.zhvk.shoppingkart.model.CartItem
import com.zhvk.shoppingkart.model.Product
import com.zhvk.shoppingkart.model.UserAddress
import com.zhvk.shoppingkart.model.Filter
import java.text.NumberFormat
import java.util.Locale

private const val TAG = "CartViewModel"

/**
 * Shared ViewModel that handles all Cart business logic
 */
class CartViewModel : ViewModel() {

    private val repository = AppRepository()

    private val _browseData = MutableLiveData<MutableList<Product>>()
    val browseData: LiveData<MutableList<Product>> get() = _browseData

    private val _filters = MutableLiveData<MutableSet<Filter>>()
    val filters: LiveData<MutableSet<Filter>> get() = _filters

    private var searchQuery: String = ""

    private val _selectedFilter = MutableLiveData<Filter?>()
    val selectedFilter: LiveData<Filter?> get() = _selectedFilter

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
        _browseData.value = getUnfilteredData()//.shuffled() as MutableList<Product>
        _filters.value = createFilters()
        resetOrder()

        // This is just for testing purposes. Should be removed
        // addRandomItems(5)
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

    fun getProduct(productId: Long) = browseData.value?.firstOrNull { it.id == productId }

    fun selectFilter(filter: Filter) {
        val previouslySelectedFilter = _selectedFilter.value

        if (filter == previouslySelectedFilter) {
            _selectedFilter.value = null
            for (f: Filter in _filters.value!!) f.isSelected = false
        } else {
            _selectedFilter.value = filter
            for (f: Filter in _filters.value!!) f.isSelected = f.name == filter.name
        }

        searchAndFilter()
    }

    fun searchProducts(query: String) {
        searchQuery = query
        if (searchQuery.isEmpty())
            _browseData.value = getUnfilteredData()
        else
            searchAndFilter()
    }

    fun getFavourites(): MutableList<Product> {
        return browseData.value?.filter { it.isFavourite } as MutableList<Product>
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

    fun cancelOrder() {
        resetOrder()
    }

    private fun getUnfilteredData(): MutableList<Product> {
        return repository.getProducts()
    }

    private fun createFilters(): MutableSet<Filter> {
        val filters: MutableSet<Filter> = mutableSetOf()
        for (product: Product in getUnfilteredData()) {
            filters.add(Filter(product.category))
        }
        return filters
    }

    private fun searchAndFilter() {
        val unfilteredData: MutableList<Product> = getUnfilteredData()
        val selectedFilter = _selectedFilter.value

        if (searchQuery.isEmpty() && selectedFilter == null) {
            _browseData.value = unfilteredData
        } else {
            var filteredList = unfilteredData.filter { it.name.lowercase(Locale.ROOT).contains(searchQuery) }

            if (selectedFilter != null)
                filteredList = filteredList.filter { it.category == selectedFilter.name }

            filteredList.sortedBy { it.price }
            _browseData.value = filteredList as MutableList<Product>?
        }
    }

    private fun resetOrder() {
        _cartItems.value = mutableListOf<CartItem>()
        _subtotalPrice.value = 0.0
        _shippingPrice.value = 0.0
        _totalPrice.value = 0.0
        _address.value = UserAddress(
            "", "", "", ""
        )
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
            val product = browseData.value?.firstOrNull { it.id == (1..21).random().toLong() }
            if (product != null) addItem(CartItem(product, (1..3).random()))
        }
    }
}