package com.zhvk.shoppingkart.model

data class UserAddress(
    var streetName: String,
    var streetNumber: String,
    var postalCode: String,
    var city: String
) {
    override fun toString(): String {
        return "$streetName $streetNumber\n$postalCode $city"
    }

    fun isSet(): Boolean {
        return !(streetName.isEmpty() ||
                streetNumber.isEmpty() ||
                postalCode.isEmpty() ||
                city.isEmpty())
    }
}
