package com.example.tzexample.data.locale.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "rubrics")
data class RubricsDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("idRubrics")
    val idRubric:Long,
    @ColumnInfo("nameRubric")
    val nameRubric:String,
    @SerializedName("imgRubric")
    val imgRubric:String
)