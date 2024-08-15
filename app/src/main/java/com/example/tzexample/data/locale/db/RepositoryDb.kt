package com.example.tzexample.data.locale.db

import javax.inject.Inject

class RepositoryDb @Inject constructor (
    private val announcedDao: AnnouncedDao
) {
    fun getListAnnounced() = announcedDao.gelAllUsers()

    suspend fun insertMedicine(announced: AnnouncedDbModel) = announcedDao.insertHistoryMedicine(announced)
    fun getAnnounced(id:String) = announcedDao.getAnnouncedDatabase(id)

    suspend fun deleteAnnounced(id:Int) = announcedDao.removeAnnounced(id)

}