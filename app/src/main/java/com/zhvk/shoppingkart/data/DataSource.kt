package com.zhvk.shoppingkart.data

import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.model.Product

/**
 * An object to generate a static list of shopping products
 */
object DataSource {

    val products: List<Product> = listOf(
        Product(
            1,
            "EchoHear Pro",
            "Over-ear",
            "These noise-cancelling over-ear headphones use cutting-edge technology to deliver crystal-clear sound, making them perfect for audiophiles and commuters alike.",
            249.99,
            true,
            R.drawable.headphones1
        ),
        Product(
            2,
            "SoundLux Elite",
            "Over-ear",
            "These over-ear headphones boast a sleek and sophisticated design, with rich, balanced sound that will transport you to a different world.",
            179.99,
            true,
            R.drawable.headphones2
        ),
        Product(
            3,
            "SonicZone 9000",
            "On-ear",
            "These on-ear headphones pack a punch with deep bass and crisp highs, all while being comfortable to wear for extended periods of time.",
            99.99,
            false,
            R.drawable.headphones3
        ),
        Product(
            4,
            "AudioBliss Max",
            "Wireless",
            "These wireless headphones offer incredible sound quality and comfort, making them the perfect choice for music lovers who demand the best.",
            200.00,
            true,
            R.drawable.headphones4
        ),
        Product(
            5,
            "BassForge 500",
            "Over-ear",
            "These headphones are specifically designed for bassheads, with powerful, deep bass that will make your music come alive.",
            129.99,
            true,
            R.drawable.headphones5
        ),
        Product(
            6,
            "PureAudio Bliss",
            "Over-ear",
            "These noise-cancelling headphones provide a pure, unadulterated listening experience, with no interruptions or distractions.",
            199.99,
            false,
            R.drawable.headphones6
        ),
        Product(
            7,
            "HiFiClouds 3000",
            "Over-ear",
            "These over-ear headphones offer a cloud-like comfort while delivering crystal-clear sound, perfect for long listening sessions.",
            279.99,
            true,
            R.drawable.headphones7
        ),
        Product(
            8,
            "ProSurge 800",
            "Over-ear",
            "These noise-cancelling headphones are engineered to provide a professional-grade audio experience, with crystal-clear sound that's perfect for studio use.",
            449.99,
            true,
            R.drawable.headphones8
        ),
        Product(
            9,
            "UltraWave HD",
            "On-ear",
            "These headphones deliver high-definition sound that is unparalleled in clarity and richness, letting you hear every detail of your favorite songs.",
            149.99,
            false,
            R.drawable.headphones9
        ),
        Product(
            10,
            "CrystalAudio X",
            "Wireless",
            "These wireless earbuds deliver crystal-clear audio with a stunningly sleek design, perfect for anyone who wants to listen to their music in style.",
            129.99,
            true,
            R.drawable.headphones10
        )
    )
}