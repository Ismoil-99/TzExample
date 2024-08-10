package com.example.tzexample.data.repositories

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.tzexample.data.models.ItemsAnnouncement
import com.example.tzexample.data.remote.apiservices.BaseApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(
    private val baseApiService: BaseApiService
) :BaseRepository {
    //    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
//    override fun getItemsAnnouncement(): Flow<ItemsAnnouncement> {
//        return flow {
//            try {
//                val response = baseApiService.getItems()
//                if (response.isSuccessful) {
//                    if (response.body() != null && response.code() == 200) {
//                        emit(response.body()!!)
//                    } else {
//                        emit(response.body()!!)
//                    }
//                } else {
//                    emit(response.body()!!)
//                }
//            } catch (e: HttpException) {
//                Log.d("error1", e.message.toString())
//            } catch (e: Throwable) {
//                Log.d("error2", e.message.toString())
//            }
//        }
//        }
    override fun getItemsAnnouncement(): Flow<ItemsAnnouncement> {
        TODO("Not yet implemented")
    }
}
