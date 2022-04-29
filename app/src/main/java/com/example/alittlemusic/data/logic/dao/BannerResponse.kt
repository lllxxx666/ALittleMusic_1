package com.example.alittlemusic.data.logic.dao
import com.google.gson.annotations.SerializedName


data class BannerResponse(
    @SerializedName("banners")
    val banners: List<Banner>,
    @SerializedName("code")
    val code: Int
)

data class Banner(
    @SerializedName("adSource")
    val adSource: Any,
    @SerializedName("adid")
    val adid: Any,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("targetId")
    val targetId: Number,
    @SerializedName("targetType")
    val targetType: Int,
    @SerializedName("titleColor")
    val titleColor: String,
    @SerializedName("typeTitle")
    val typeTitle: String,
    @SerializedName("url")
    val url: String
)