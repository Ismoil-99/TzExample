package com.example.tzexample.data.locale.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AnnouncedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistoryMedicine(announcedDbModel: AnnouncedDbModel)

    @Query("Select * from announced")
    fun gelAllUsers(): LiveData<List<AnnouncedDbModel>>

    @Query("SELECT * FROM announced WHERE id == :id")
    fun getAnnouncedDatabase(id: String): LiveData<AnnouncedDbModel>

    @Query("DELETE FROM announced WHERE id = :id")
    suspend fun removeAnnounced(id: Int)
}