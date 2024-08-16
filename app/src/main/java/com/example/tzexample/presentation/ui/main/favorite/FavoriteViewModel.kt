package com.example.tzexample.presentation.ui.main.favorite

import androidx.lifecycle.ViewModel
import com.example.tzexample.data.locale.db.RepositoryFavoriteDb
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repositoryFavoriteDb: RepositoryFavoriteDb
) :ViewModel() {
    fun showAnnouncedFavorite(id:String) = repositoryFavoriteDb.getAnnounced(id)

    fun getCountAnnouncedFavorite() = repositoryFavoriteDb.getListAnnounced()

    suspend fun deleteFavorite(id: Int) = repositoryFavoriteDb.deleteAnnounced(id)
}