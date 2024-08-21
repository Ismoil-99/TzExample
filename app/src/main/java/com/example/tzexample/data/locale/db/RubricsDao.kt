package com.example.tzexample.data.locale.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RubricsDao {
    @Query("Select * from rubrics")
    fun gelAllRubrics(): LiveData<List<RubricsDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRubrics(announcedCountLocal: RubricsDbModel)
}