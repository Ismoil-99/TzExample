package com.example.tzexample.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tzexample.data.models.Announcement
import com.example.tzexample.data.remote.apiservices.BaseApiService
import javax.inject.Inject

class AnnouncementDataSource @Inject constructor (
    private val baseApiService: BaseApiService
) : PagingSource<Int, Announcement>() {
    override fun getRefreshKey(state: PagingState<Int, Announcement>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Announcement> {
        return try {
            val position = params.key ?: 1
            val data = baseApiService.getItems(position)
            LoadResult.Page(
                data = data.body()?.announcement!!,
                prevKey = if (position == 1) null else position - 1,
                nextKey =  position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}