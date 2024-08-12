package com.example.tzexample.presentation.main.search

import androidx.lifecycle.ViewModel
import com.example.tzexample.data.repositories.BaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RubricsViewModel @Inject constructor(
    private val baseRepositoryImpl: BaseRepositoryImpl
) : ViewModel() {

    fun rubrics() = baseRepositoryImpl.rubricsAnnounced()
}