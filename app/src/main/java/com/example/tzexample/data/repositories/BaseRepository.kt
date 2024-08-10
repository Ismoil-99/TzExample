package com.example.tzexample.data.repositories

import com.example.tzexample.data.models.ItemsAnnouncement


interface BaseRepository {
    fun getItemsAnnouncement(): kotlinx.coroutines.flow.Flow<ItemsAnnouncement>
}