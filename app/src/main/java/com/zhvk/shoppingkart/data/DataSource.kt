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
            "SoundLux Pro",
            "Wireless headphones",
            "These noise-cancelling Wireless headphones use cutting-edge technology to deliver crystal-clear sound, making them perfect for audiophiles and commuters alike.",
            249.00,
            true,
            listOf(R.drawable.headphones1_1, R.drawable.headphones1_2)
        ),
        Product(
            2,
            "EchoHear Elite",
            "Wireless headphones",
            "These wireless headphones boast a sleek and sophisticated design, with rich, balanced sound that will transport you to a different world.",
            179.99,
            true,
            listOf(R.drawable.headphones2)
        ),
        /*Product(
            3,
            "SonicZone 9000",
            "Wireless headphones",
            "These wireless headphones pack a punch with deep bass and crisp highs, all while being comfortable to wear for extended periods of time.",
            299.99,
            false,
            listOf(R.drawable.headphones3)
        ),*/
        Product(
            4,
            "AudioBliss Max",
            "Wireless headphones",
            "These wireless headphones offer incredible sound quality and comfort, making them the perfect choice for music lovers who demand the best.",
            209.00,
            true,
            listOf(R.drawable.headphones4_1, R.drawable.headphones4_2, R.drawable.headphones4_3)
        ),
        Product(
            5,
            "BassForge 500",
            "Wireless headphones",
            "These headphones are specifically designed for bassheads, with powerful, deep bass that will make your music come alive.",
            129.99,
            true,
            listOf(R.drawable.headphones5)
        ),
        Product(
            6,
            "PureAudio Bliss",
            "Wireless headphones",
            "These noise-cancelling headphones provide a pure, unadulterated listening experience, with no interruptions or distractions.",
            399.00,
            false,
            listOf(R.drawable.headphones3, R.drawable.headphones6)
        ),
        Product(
            7,
            "HiFiClouds 3000",
            "On-ear headphones",
            "These on-ear headphones offer a cloud-like comfort while delivering crystal-clear sound, perfect for long listening sessions.",
            279.99,
            true,
            listOf(R.drawable.headphones9)
        ),
        Product(
            8,
            "ProSurge 800",
            "Over-ear headphones",
            "These noise-cancelling headphones are engineered to provide a professional-grade audio experience, with crystal-clear sound that's perfect for studio use.",
            249.99,
            true,
            listOf(R.drawable.headphones10_1, R.drawable.headphones10_2)
        ),
        Product(
            9,
            "UltraWave HD",
            "Headphone amplifier",
            "This amplifier is designed to provide a pure, unadulterated listening experience, with no coloration or distortion in the sound. The minimalist design is both elegant and functional, making it a great addition to any home audio system.",
            349.99,
            false,
            listOf(R.drawable.gear1_1, R.drawable.gear1_2)
        ),
        Product(
            10,
            "CrystalAudio X",
            "Earbuds",
            "These wireless earbuds deliver crystal-clear audio with a stunningly sleek design, perfect for anyone who wants to listen to their music in style.",
            129.00,
            true,
            listOf(R.drawable.earbuds1_1, R.drawable.earbuds1_2, R.drawable.earbuds1_3)
        ),
        Product(
            11,
            "StellarX Pro",
            "Amplifier",
            "This high-end amplifier delivers rich and detailed sound, with plenty of power to drive even the most demanding speakers. The sleek and modern design is sure to impress any audiophile.",
            639.99,
            true,
            listOf(R.drawable.amp1_1, R.drawable.amp1_2)
        ),
        Product(
            12,
            "PowerMax Ultra",
            "Amplifier",
            "This powerful amplifier is designed to provide an unparalleled listening experience, with crystal-clear sound and incredible dynamics. The solid construction ensures long-lasting durability, making it a great investment for any serious audiophile.",
            1199.99,
            true,
            listOf(R.drawable.amp2)
        ),
        Product(
            13,
            "MiniWave Elite",
            "Mini Amp",
            "Don't let its small size fool you, this mini amplifier delivers big sound in a compact package. The sleek and minimalist design is perfect for small spaces, while the advanced circuitry ensures clear and dynamic sound. It's perfect for powering bookshelf speakers, and its portability makes it great for on-the-go listening.",
            129.00,
            false,
            listOf(R.drawable.amp3)
        ),
        Product(
            14,
            "MegaWatt Pro",
            "Amplifier",
            "This amplifier delivers an astonishing amount of power, with the ability to drive even the largest and most demanding speakers with ease. The advanced circuitry ensures low distortion and high clarity, making it perfect for audiophiles who demand the best. The solid construction and elegant design make it a standout addition to any home audio system.",
            559.99,
            true,
            listOf(R.drawable.amp4)
        ),
        Product(
            15,
            "EchoMic Pro",
            "Dynamic microphone",
            "This high-quality dynamic microphone is perfect for live performances and recording sessions. The dynamic capsule provides a clear and accurate sound, while the rugged construction ensures durability.",
            149.00,
            true,
            listOf(R.drawable.mic1_1, R.drawable.mic1_2)
        ),
        Product(
            16,
            "UltraVoice Condenser Mic",
            "Cardioid microphone",
            "This cardioid condenser microphone is perfect for capturing clear and detailed vocals, with a wide frequency response and high sensitivity. The sleek and modern design looks great in any recording studio or home setup.",
            199.00,
            false,
            listOf(R.drawable.mic2)
        ),
        Product(
            17,
            "PureSound Ribbon Mic",
            "Bidirectional microphone",
            "This bidirectional ribbon microphone is perfect for recording acoustic instruments and vocals, with a natural and warm sound. The ribbon element provides excellent transient response and a smooth high-end, making it a favorite among audiophiles.",
            299.00,
            true,
            listOf(R.drawable.mic3)
        ),
        Product(
            18,
            "OmniMic Pro",
            "Omnidirectional Condenser microphone",
            "This omnidirectional condenser microphone is perfect for capturing a room's ambience or recording group vocals. It features a wide frequency response and low self-noise, making it a great choice for professional recordings.",
            249.99,
            true,
            listOf(R.drawable.mic4_1, R.drawable.mic4_2)
        ),
        Product(
            19,
            "SonicWave Bookshelf Speakers",
            "Passive speakers",
            "These bookshelf speakers deliver stunning sound quality and a wide soundstage, with a frequency response of 45 Hz - 25 kHz. The passive design requires an external amplifier, but the result is a clear and detailed sound that will elevate any audio experience.",
            399.00,
            true,
            listOf(R.drawable.speaker2_1, R.drawable.speaker2_2)
        ),
        Product(
            20,
            "UltraSound Mini Speaker",
            "Active speaker",
            "These mini speaker delivers a powerful and dynamic sound, with a built-in amplifier and a frequency response of 30 Hz - 20 kHz. The sleek and modern design looks great in any home audio system, and the ease of use makes them perfect for any audiophile.",
            559.99,
            false,
            listOf(R.drawable.speaker1_1, R.drawable.speaker1_2)
        ),
        Product(
            21,
            "PureAudio Floorstanding Speakers ",
            "Passive speakers",
            "These floorstanding speakers provide a clear and detailed sound with a frequency response of 28 Hz - 25 kHz. The passive design requires an external amplifier, but the result is a stunning and immersive audio experience that will delight any audiophile.",
            1499.99,
            true,
            listOf(R.drawable.speaker3)
        )
    ).shuffled()
}