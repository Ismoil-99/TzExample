package com.example.tzexample.data.locale.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "announced")
data class AnnouncedDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("name")
    val nameAnnounced:String,
    @ColumnInfo("content")
    val contentAnnounced:String,
    @ColumnInfo("city")
    val cityAnnounced:String,
    @ColumnInfo("telephone")
    val telAnnounced:String,
    @ColumnInfo(name = "imageData", typeAffinity = ColumnInfo.BLOB)
    val imageAnnounced:ByteArray
)