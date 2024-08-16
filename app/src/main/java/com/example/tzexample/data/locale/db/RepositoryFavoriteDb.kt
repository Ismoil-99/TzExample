package com.example.tzexample.data.locale.db

import javax.inject.Inject

class RepositoryFavoriteDb @Inject constructor(
    private val favoriteAnnouncedDao: FavoriteAnnouncedDao
) {
    fun getListAnnounced() = favoriteAnnouncedDao.gelFavoriteAnnounced()

    suspend fun insertFavorite(announced: FavoriteDbModel) = favoriteAnnouncedDao.insertFavoriteAnnounced(announced)
    fun getAnnounced(id:String) = favoriteAnnouncedDao.getAnnouncedFavorite(id)

    suspend fun deleteAnnounced(id:Int) = favoriteAnnouncedDao.removeAnnounced(id)
}