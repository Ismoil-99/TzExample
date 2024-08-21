package com.example.tzexample.data.locale.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("count")
data class AnnouncedCountLocal(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("countAnnounced")
    val countAnnounced:String,
)