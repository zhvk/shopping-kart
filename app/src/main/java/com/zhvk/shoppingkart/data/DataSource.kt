package com.zhvk.shoppingkart.data

import com.zhvk.shoppingkart.R
import com.zhvk.shoppingkart.model.Product
import com.zhvk.shoppingkart.model.TutorialPageInfo

/**
 * An object to generate a static list of shopping products
 */
object DataSource {

    val tutorialPageInfoList: List<TutorialPageInfo> = listOf(
        TutorialPageInfo(
            title = "Welcome",
            description = "Welcome to our Hi-Fi mobile app shop! Discover the latest and greatest audio products, compare features, and find your perfect audio companion",
            animationResource = R.raw.manage_online_shop
        ),
        TutorialPageInfo(
            title = "Save Favourites",
            description = "Never lose track of your favorite products! Save items to your wishlist and easily access them whenever you're ready to make a purchase",
            animationResource = R.raw.isometric_delivery_box
        ),
        TutorialPageInfo(
            title = "Checkout & Await",
            description = "Double-check your order details and confirm your purchase. Now, sit back and eagerly await the arrival of your chosen audio products",
            animationResource = R.raw.door_delivery
        )
    )

    val products: MutableList<Product> = mutableListOf(
        Product(
            id = 1,
            name = "SoundLux Pro",
            category = "Headphones",
            type = "Wireless",
            description = "These noise-cancelling Wireless headphones use cutting-edge technology to deliver crystal-clear sound, making them perfect for audiophiles and commuters alike.",
            price = 249.00,
            isAvailable = true,
            isFavourite = true,
            imageResourceIds = listOf(R.drawable.headphones1_1, R.drawable.headphones1_2)
        ),
        Product(
            id = 2,
            name = "EchoHear Elite",
            category = "Headphones",
            type = "Wireless",
            description = "These wireless headphones boast a sleek and sophisticated design, with rich, balanced sound that will transport you to a different world.",
            price = 179.99,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.headphones2)
        ),
        Product(
            id = 3,
            name = "SonicZone 9000",
            category = "Headphones",
            type = "Wireless",
            description = "These wireless headphones pack a punch with deep bass and crisp highs, all while being comfortable to wear for extended periods of time.",
            price = 299.99,
            isAvailable = false,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.headphones3)
        ),
        Product(
            id = 4,
            name = "AudioBliss Max",
            category = "Headphones",
            type = "Wireless",
            description = "These wireless headphones offer incredible sound quality and comfort, making them the perfect choice for music lovers who demand the best.",
            price = 209.00,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(
                R.drawable.headphones4_1,
                R.drawable.headphones4_2,
                R.drawable.headphones4_3
            )
        ),
        Product(
            id = 5,
            name = "BassForge 500",
            category = "Headphones",
            type = "Wireless",
            description = "These headphones are specifically designed for bassheads, with powerful, deep bass that will make your music come alive.",
            price = 129.99,
            isAvailable = true,
            isFavourite = true,
            imageResourceIds = listOf(R.drawable.headphones5)
        ),
        Product(
            id = 6,
            name = "PureAudio Bliss",
            category = "Headphones",
            type = "Wireless",
            description = "These noise-cancelling headphones provide a pure, unadulterated listening experience, with no interruptions or distractions.",
            price = 399.00,
            isAvailable = false,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.headphones3, R.drawable.headphones6)
        ),
        Product(
            id = 7,
            name = "HiFiClouds 3000",
            category = "Headphones",
            type = "On-ear",
            description = "These on-ear headphones offer a cloud-like comfort while delivering crystal-clear sound, perfect for long listening sessions.",
            price = 279.99,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.headphones9)
        ),
        Product(
            id = 8,
            name = "ProSurge 800",
            category = "Headphones",
            type = "Over-ear",
            description = "These noise-cancelling headphones are engineered to provide a professional-grade audio experience, with crystal-clear sound that's perfect for studio use.",
            price = 249.99,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.headphones10_1, R.drawable.headphones10_2)
        ),
        Product(
            id = 9,
            name = "UltraWave HD",
            category = "Amplifier",
            type = "Headphone amplifier",
            description = "This amplifier is designed to provide a pure, unadulterated listening experience, with no coloration or distortion in the sound. The minimalist design is both elegant and functional, making it a great addition to any home audio system.",
            price = 349.99,
            isAvailable = false,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.gear1_1, R.drawable.gear1_2)
        ),
        Product(
            id = 10,
            name = "CrystalAudio X",
            category = "Earbuds",
            type = "Wireless",
            description = "These wireless earbuds deliver crystal-clear audio with a stunningly sleek design, perfect for anyone who wants to listen to their music in style.",
            price = 129.00,
            isAvailable = true,
            isFavourite = true,
            imageResourceIds = listOf(
                R.drawable.earbuds1_1,
                R.drawable.earbuds1_2,
                R.drawable.earbuds1_3
            )
        ),
        Product(
            id = 11,
            name = "StellarX Pro",
            category = "Amplifier",
            type = "Regular",
            description = "This high-end amplifier delivers rich and detailed sound, with plenty of power to drive even the most demanding speakers. The sleek and modern design is sure to impress any audiophile.",
            price = 639.99,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.amp1_1, R.drawable.amp1_2)
        ),
        Product(
            id = 12,
            name = "PowerMax Ultra",
            category = "Amplifier",
            type = "Regular",
            description = "This powerful amplifier is designed to provide an unparalleled listening experience, with crystal-clear sound and incredible dynamics. The solid construction ensures long-lasting durability, making it a great investment for any serious audiophile.",
            price = 1199.99,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.amp2)
        ),
        Product(
            id = 13,
            name = "MiniWave Elite",
            category = "Amplifier",
            type = "Mini Amp",
            description = "Don't let its small size fool you, this mini amplifier delivers big sound in a compact package. The sleek and minimalist design is perfect for small spaces, while the advanced circuitry ensures clear and dynamic sound. It's perfect for powering bookshelf speakers, and its portability makes it great for on-the-go listening.",
            price = 129.00,
            isAvailable = false,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.amp3)
        ),
        Product(
            id = 14,
            name = "MegaWatt Pro",
            category = "Amplifier",
            type = "Regular",
            description = "This amplifier delivers an astonishing amount of power, with the ability to drive even the largest and most demanding speakers with ease. The advanced circuitry ensures low distortion and high clarity, making it perfect for audiophiles who demand the best. The solid construction and elegant design make it a standout addition to any home audio system.",
            price = 559.99,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.amp4)
        ),
        Product(
            id = 15,
            name = "EchoMic Pro",
            category = "Microphone",
            type = "Dynamic microphone",
            description = "This high-quality dynamic microphone is perfect for live performances and recording sessions. The dynamic capsule provides a clear and accurate sound, while the rugged construction ensures durability.",
            price = 149.00,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.mic1_1, R.drawable.mic1_2)
        ),
        Product(
            id = 16,
            name = "UltraVoice Condenser Mic",
            category = "Microphone",
            type = "Cardioid microphone",
            description = "This cardioid condenser microphone is perfect for capturing clear and detailed vocals, with a wide frequency response and high sensitivity. The sleek and modern design looks great in any recording studio or home setup.",
            price = 199.00,
            isAvailable = false,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.mic2)
        ),
        Product(
            id = 17,
            name = "PureSound Ribbon Mic",
            category = "Microphone",
            type = "Bidirectional microphone",
            description = "This bidirectional ribbon microphone is perfect for recording acoustic instruments and vocals, with a natural and warm sound. The ribbon element provides excellent transient response and a smooth high-end, making it a favorite among audiophiles.",
            price = 299.00,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.mic3)
        ),
        Product(
            id = 18,
            name = "OmniMic Pro",
            category = "Microphone",
            type = "Omnidirectional Condenser microphone",
            description = "This omnidirectional condenser microphone is perfect for capturing a room's ambience or recording group vocals. It features a wide frequency response and low self-noise, making it a great choice for professional recordings.",
            price = 249.99,
            isAvailable = true,
            isFavourite = true,
            imageResourceIds = listOf(R.drawable.mic4_1, R.drawable.mic4_2)
        ),
        Product(
            id = 19,
            name = "SonicWave Bookshelf Speakers",
            category = "Speakers",
            type = "Passive",
            description = "These bookshelf speakers deliver stunning sound quality and a wide soundstage, with a frequency response of 45 Hz - 25 kHz. The passive design requires an external amplifier, but the result is a clear and detailed sound that will elevate any audio experience.",
            price = 399.00,
            isAvailable = true,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.speaker2_1, R.drawable.speaker2_2)
        ),
        Product(
            id = 20,
            name = "UltraSound Mini Speaker",
            category = "Speakers",
            type = "Active",
            description = "These mini speaker delivers a powerful and dynamic sound, with a built-in amplifier and a frequency response of 30 Hz - 20 kHz. The sleek and modern design looks great in any home audio system, and the ease of use makes them perfect for any audiophile.",
            price = 559.99,
            isAvailable = false,
            isFavourite = false,
            imageResourceIds = listOf(R.drawable.speaker1_1, R.drawable.speaker1_2)
        ),
        Product(
            id = 21,
            name = "PureAudio Floorstanding Speakers ",
            category = "Speakers",
            type = "Passive",
            description = "These floorstanding speakers provide a clear and detailed sound with a frequency response of 28 Hz - 25 kHz. The passive design requires an external amplifier, but the result is a stunning and immersive audio experience that will delight any audiophile.",
            price = 1499.99,
            isAvailable = true,
            isFavourite = true,
            imageResourceIds = listOf(R.drawable.speaker3)
        )
    )
}