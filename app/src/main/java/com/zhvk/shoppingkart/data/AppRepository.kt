package com.zhvk.shoppingkart.data

import androidx.lifecycle.MutableLiveData
import com.zhvk.shoppingkart.model.Product

class AppRepository(
//    private val database: AppDatabase
) {

    private val products = MutableLiveData<MutableList<Product>>()

    init {
        products.value = DataSource.products
    }

    fun getProducts(): MutableList<Product> {
        return products.value ?: mutableListOf()
    }
}