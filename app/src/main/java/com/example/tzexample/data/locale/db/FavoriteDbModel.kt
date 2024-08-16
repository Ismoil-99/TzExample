package com.example.tzexample.data.locale.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite")
data class FavoriteDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("name")
    val nameFavoriteAnnounced:String,
    @ColumnInfo("content")
    val contentFavoriteAnnounced:String,
    @ColumnInfo("price")
    val priceFavoriteAnnounced:Long,
    @ColumnInfo(name = "imageData",)
    val imageFavoriteAnnounced:String
)