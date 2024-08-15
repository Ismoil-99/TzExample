package com.example.tzexample.presentation.ui.main.more.myannounced

import androidx.lifecycle.ViewModel
import com.example.tzexample.data.locale.db.RepositoryDb
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyAnnouncedViewModel @Inject constructor(
    private val repositoryDb: RepositoryDb
):ViewModel() {
    fun getAnnounced() = repositoryDb.getListAnnounced()

    fun getAnnouncedItem(id:String) = repositoryDb.getAnnounced(id)

    suspend fun deleteAnnounced(id:Int) = repositoryDb.deleteAnnounced(id)

}