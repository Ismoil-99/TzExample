package com.example.tzexample.data.locale.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AnnouncedCountDao {
    @Query("Select * from count")
    fun gelAllCountAnnounced(): LiveData<List<AnnouncedCountLocal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistoryMedicine(announcedCountLocal: AnnouncedCountLocal)
}