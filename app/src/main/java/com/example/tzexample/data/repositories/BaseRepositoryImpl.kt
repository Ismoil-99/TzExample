package com.example.tzexample.data.repositories

import android.util.Log
import com.example.tzexample.data.models.Announcement
import com.example.tzexample.data.remote.apiservices.BaseApiService
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(
    private val baseApiService: BaseApiService
) :BaseRepository {
    override fun showAnnounced(id: String): Flow<Announcement> {
        return flow {
            try {
                val response = baseApiService.showAnnounced(id)
                if (response.isSuccessful) {
                    if (response.body() != null && response.code() == 200) {
                        emit(response.body()!!)
                    } else {
                        emit(response.body()!!)
                    }
                } else {
                    emit(response.body()!!)
                }
            } catch (e: HttpException) {
                Log.d("error1", e.message.toString())
            } catch (e: Throwable) {
                Log.d("error3", e.message.toString())
            }
        }
    }

}
