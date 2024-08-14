package com.example.tzexample.data.locale.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnnouncedDbModel::class,
], version = 2, exportSchema = false)
abstract class LocaleDataBase : RoomDatabase() {

    abstract fun announcedDb(): AnnouncedDao

    companion object{
        private var DB_INSTANCE: LocaleDataBase? = null
        fun getAppDBInstance(context: Context): LocaleDataBase {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    LocaleDataBase::class.java,
                    "tzlocale"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return DB_INSTANCE!!
        }
    }
}