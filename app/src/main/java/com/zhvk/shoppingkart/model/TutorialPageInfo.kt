package com.zhvk.shoppingkart.model

import androidx.annotation.RawRes

data class TutorialPageInfo(
    val title: String,
    val description: String,
    @RawRes val animationResource: Int
)
