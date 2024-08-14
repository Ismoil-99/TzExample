package com.example.tzexample.presentation.ui.main.add.addannounced

import androidx.lifecycle.ViewModel
import com.example.tzexample.data.locale.db.AnnouncedDbModel
import com.example.tzexample.data.locale.db.RepositoryDb
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAnnouncedViewModel @Inject constructor(private val repositoryDb: RepositoryDb) :ViewModel() {
       suspend  fun insertAnnounced(announcedDbModel: AnnouncedDbModel) = repositoryDb.insertMedicine(announcedDbModel)
}