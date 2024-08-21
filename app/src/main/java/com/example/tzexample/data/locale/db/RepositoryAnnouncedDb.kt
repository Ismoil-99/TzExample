package com.example.tzexample.data.locale.db

import javax.inject.Inject

class RepositoryAnnouncedDb @Inject constructor(
    private val announcedCountLocal: AnnouncedCountDao
) {
    fun getListAnnounced() = announcedCountLocal.gelAllCountAnnounced()
    suspend fun insertFavorite(announced: AnnouncedCountLocal) = announcedCountLocal.insertHistoryMedicine(announced)

}