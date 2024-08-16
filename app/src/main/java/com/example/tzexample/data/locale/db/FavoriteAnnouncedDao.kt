package com.example.tzexample.data.locale.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteAnnouncedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteAnnounced(favoriteDbModel: FavoriteDbModel)

    @Query("Select * from favorite")
    fun gelFavoriteAnnounced(): LiveData<List<FavoriteDbModel>>

    @Query("SELECT * FROM favorite WHERE id == :id")
    fun getAnnouncedFavorite(id: String): LiveData<FavoriteDbModel>

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun removeAnnounced(id: Int)
}