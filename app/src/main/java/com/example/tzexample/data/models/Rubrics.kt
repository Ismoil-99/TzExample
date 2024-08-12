package com.example.tzexample.data.models

import com.google.gson.annotations.SerializedName

data class Rubrics(
    @SerializedName("id")
    val idRubrics:Long,
    @SerializedName("name")
    val nameRubric:String,
    @SerializedName("img")
    val imgRubric:String
)