package com.example.swing_assignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import com.example.swing_assignment.data.retrofit.RetrofitClient
import com.example.swing_assignment.data.retrofit.RetrofitInterface
import com.example.swing_assignment.domain.ImageRepository
import com.example.swing_assignment.ui.ImagePagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ImageRepositoryImpl(private val api : RetrofitInterface) : ImageRepository {
    override suspend fun getSearchImageData(query: String): Flow<PagingData<ImageDataModel>> {
        return  Pager(PagingConfig(30)) {
            ImagePagingSource(api, query)
        }.flow
    }
}