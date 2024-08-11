package com.example.tzexample.data.repositories

import com.example.tzexample.data.models.Announcement
import com.example.tzexample.presentation.extensions.UIState
import kotlinx.coroutines.flow.Flow


interface BaseRepository {
    fun showAnnounced(id:String):Flow<UIState<Announcement>>
}