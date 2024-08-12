package com.example.tzexample.data.remote.apiservices

import com.example.tzexample.data.models.Announcement
import com.example.tzexample.data.models.Category
import com.example.tzexample.data.models.ItemsAnnouncement
import com.example.tzexample.data.models.Rubrics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BaseApiService {
    @GET("api/items/")
    suspend fun getItems(
        @Query("page") page: Int,
    ):Response<ItemsAnnouncement>

    @GET("api/items/{id}")
    suspend fun showAnnounced(
        @Path("id") id:String,
    ):Response<Announcement>

    @GET("api/items/rubrics/")
    suspend fun rubricsAnnounced():Response<List<Rubrics>>

    @GET("api/items/rubrics/{id}/")
    suspend fun categoryRubrics(
        @Path("id") id:String,
    ):Response<List<Category>>

}