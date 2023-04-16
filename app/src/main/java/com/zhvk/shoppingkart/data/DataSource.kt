package com.zhvk.shoppingkart.data

import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.model.Product

/**
 * An object to generate a static list of shopping products
 */
object DataSource {

    val products: List<Product> = listOf(
        Product(
            "AWH N13BT",
            "Wireless Headphones",
            "The new AWH N13BT delivers active noise cancellation, high-quality wireless codec support and Bluetooth® 5.0.",
            200.00,
            true,
            R.drawable.awh_n13bt
        ),
        Product(
            "VHR N700C2",
            "Wireless Headphones",
            "VHR N700C2 wireless headphones are engineered with renowned noise cancellation. With Google Assistant and Amazon Alexa built-in, you have instant access to millions of songs, playlists and more.",
            200.00,
            true,
            R.drawable.vhr_n700c2
        ),
        Product(
            "Beets Extra2",
            "Cable Headphones",
            "Beets Extra2 over-ear headphones delivers premium sound while blocking external noise with Active Noise Cancelling",
            200.00,
            true,
            R.drawable.beets_extra2
        )
    )
}