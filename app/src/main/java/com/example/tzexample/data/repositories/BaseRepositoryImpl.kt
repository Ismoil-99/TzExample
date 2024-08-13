package com.example.tzexample.data.repositories

import android.util.Log
import com.example.tzexample.data.models.Announcement
import com.example.tzexample.data.models.Category
import com.example.tzexample.data.models.ItemsAnnouncement
import com.example.tzexample.data.models.Rubrics
import com.example.tzexample.data.remote.apiservices.BaseApiService
import com.example.tzexample.presentation.extensions.UIState
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(
    private val baseApiService: BaseApiService
) :BaseRepository {
    override fun showAnnounced(id: String): Flow<UIState<Announcement>> {
        return flow {
            emit(UIState.Loading())
            delay(500)
            try {
                val response = baseApiService.showAnnounced(id)
                if (response.isSuccessful) {
                    if (response.body() != null && response.code() == 200) {
                        emit(UIState.Success(data = response.body()!!))
                    } else {
                        emit(UIState.Error(message = response.code().toString()))
                    }
                } else {
                    emit(UIState.Error(message = response.code().toString()))
                }
            } catch (e: HttpException) {
                emit(UIState.Error(message = e.message.toString()))
            } catch (e: Throwable) {
                emit(UIState.Error(message = e.message.toString()))
            }
        }
    }

    override fun getCountAnnounced(): Flow<UIState<ItemsAnnouncement>> {
        return flow {
            emit(UIState.Loading())
            try {
                val response = baseApiService.getItems(1)
                if (response.isSuccessful) {
                    if (response.body() != null && response.code() == 200) {
                        emit(UIState.Success(data = response.body()!!))
                    } else {
                        emit(UIState.Error(message = response.code().toString()))
                    }
                } else {
                    emit(UIState.Error(message = response.code().toString()))
                }
            } catch (e: HttpException) {
                emit(UIState.Error(message = e.message.toString()))
            } catch (e: Throwable) {
                emit(UIState.Error(message = e.message.toString()))
            }
        }
    }

    override fun rubricsAnnounced(): Flow<UIState<List<Rubrics>>> {
        return flow {
            emit(UIState.Loading())
            try {
                val response = baseApiService.rubricsAnnounced()
                if (response.isSuccessful) {
                    if (response.body() != null && response.code() == 200) {
                        emit(UIState.Success(data = response.body()!!))
                    } else {
                        emit(UIState.Error(message = response.code().toString()))
                    }
                } else {
                    emit(UIState.Error(message = response.code().toString()))
                }
            } catch (e: HttpException) {
                emit(UIState.Error(message = e.message.toString()))
            } catch (e: Throwable) {
                emit(UIState.Error(message = e.message.toString()))
            }
        }
    }

    override fun categoryRubric(id: String): Flow<UIState<List<Category>>> {
        return flow {
            emit(UIState.Loading())
            try {
                val response = baseApiService.categoryRubrics(id)
                if (response.isSuccessful) {
                    if (response.body() != null && response.code() == 200) {
                        emit(UIState.Success(data = response.body()!!))
                    } else {
                        emit(UIState.Error(message = response.code().toString()))
                    }
                } else {
                    emit(UIState.Error(message = response.code().toString()))
                }
            } catch (e: HttpException) {
                emit(UIState.Error(message = e.message.toString()))
            } catch (e: Throwable) {
                emit(UIState.Error(message = e.message.toString()))
            }
        }
    }

    override fun getListAnnounced(): Flow<UIState<ItemsAnnouncement>> {
        return flow {
            emit(UIState.Loading())
            try {
                val response = baseApiService.getItems(2)
                if (response.isSuccessful) {
                    if (response.body() != null && response.code() == 200) {
                        emit(UIState.Success(data = response.body()!!))
                    } else {
                        emit(UIState.Error(message = response.code().toString()))
                    }
                } else {
                    emit(UIState.Error(message = response.code().toString()))
                }
            } catch (e: HttpException) {
                emit(UIState.Error(message = e.message.toString()))
            } catch (e: Throwable) {
                emit(UIState.Error(message = e.message.toString()))
            }
        }
    }


}
