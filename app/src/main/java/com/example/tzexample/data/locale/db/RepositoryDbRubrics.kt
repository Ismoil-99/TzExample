package com.example.tzexample.data.locale.db

import javax.inject.Inject

class RepositoryDbRubrics @Inject constructor(
    private val favoriteAnnouncedDao: RubricsDao
) {
    fun getListAnnounced() = favoriteAnnouncedDao.gelAllRubrics()
    suspend fun insertRubrics(announced: RubricsDbModel) = favoriteAnnouncedDao.insertRubrics(announced)

}