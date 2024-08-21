package com.example.tzexample.presentation.ui.main.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.tzexample.data.datasource.AnnouncementDataSource
import com.example.tzexample.data.locale.db.AnnouncedCountLocal
import com.example.tzexample.data.locale.db.FavoriteDbModel
import com.example.tzexample.data.locale.db.RepositoryAnnouncedDb
import com.example.tzexample.data.locale.db.RepositoryFavoriteDb
import com.example.tzexample.data.remote.apiservices.BaseApiService
import com.example.tzexample.data.repositories.BaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val baseRepositoryImpl: BaseRepositoryImpl,
    private val baseApiService: BaseApiService,
    private val favoriteDb: RepositoryFavoriteDb,
    private val repositoryAnnouncedDb: RepositoryAnnouncedDb
) :ViewModel() {

    var listData = Pager(config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { AnnouncementDataSource(baseApiService,) }
    ).flow.cachedIn(viewModelScope)

     fun showAnnounced(id:String) = baseRepositoryImpl.showAnnounced(id)

    fun getCountAnnounced() = baseRepositoryImpl.getCountAnnounced()

    suspend fun insertFavorite(
        favoriteDbModel: FavoriteDbModel
    ) = favoriteDb.insertFavorite(favoriteDbModel)
    suspend fun insertDbCount(
        announcedCountLocal: AnnouncedCountLocal
    ) = repositoryAnnouncedDb.insertFavorite(announcedCountLocal)

    fun rubrics() = baseRepositoryImpl.rubricsAnnounced()

    fun getCount() = repositoryAnnouncedDb.getListAnnounced()
}