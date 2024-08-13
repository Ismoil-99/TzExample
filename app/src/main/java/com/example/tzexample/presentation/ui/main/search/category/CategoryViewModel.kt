package com.example.tzexample.presentation.ui.main.search.category

import androidx.lifecycle.ViewModel
import com.example.tzexample.data.repositories.BaseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val baseRepositoryImpl: BaseRepositoryImpl
) :ViewModel() {
    fun categoryRubrics(id:String) = baseRepositoryImpl.categoryRubric(id)

    fun showAnnounced() = baseRepositoryImpl.getListAnnounced()
}