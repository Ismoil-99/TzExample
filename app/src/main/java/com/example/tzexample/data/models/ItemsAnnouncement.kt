package com.example.tzexample.data.models

import com.google.gson.annotations.SerializedName

data class ItemsAnnouncement(
    @SerializedName("count")
    val countAnnouncement: Long,
    @SerializedName("results")
    val announcement: List<Announcement>
)