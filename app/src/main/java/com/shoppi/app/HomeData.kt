package com.shoppi.app

import com.google.gson.annotations.SerializedName

data class HomeData(
    val title: Title,
    @SerializedName("top_banners") val top_banners: List<Banner>
)
