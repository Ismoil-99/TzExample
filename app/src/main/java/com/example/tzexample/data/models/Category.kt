package com.example.tzexample.data.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val idCategory:Long,
    @SerializedName("name")
    val nameCategory: String
)