package com.example.swing_assignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.retrofit.RetrofitApi
import com.example.swing_assignment.domain.repository.ImageRepository
import com.example.swing_assignment.data.paging.ImagePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor(private val retrofitInstance : RetrofitApi) : ImageRepository {
    override fun getSearchImageData(query: String): Flow<PagingData<ImageDataModel>> {
        return  Pager(PagingConfig(pageSize = 30, initialLoadSize = 30, enablePlaceholders = false)) {
            ImagePagingSource(retrofitInstance, query)
        }.flow
    }
}