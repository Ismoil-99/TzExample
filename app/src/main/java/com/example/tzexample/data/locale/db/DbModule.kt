package com.example.tzexample.data.locale.db

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Application): LocaleDataBase {
        return LocaleDataBase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(appDatabase: LocaleDataBase): AnnouncedDao {
        return appDatabase.announcedDb()
    }

}