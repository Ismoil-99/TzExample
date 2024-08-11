package com.example.tzexample.data.models

import com.google.gson.annotations.SerializedName

data class Announcement(
    @SerializedName("id")
    val idAnnouncement: Long,
    @SerializedName("title")
    val name:String,
    @SerializedName("description")
    val contentAnnouncement: String,
    @SerializedName("price")
    val priceAnnouncement: Long,
    @SerializedName("images")
    val imagesAnnouncement:List<ImageAnnouncement>
)

data class ImageAnnouncement(
    @SerializedName("orig")
    val iconUrl:String
)
