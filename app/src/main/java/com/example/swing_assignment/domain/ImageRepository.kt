package com.example.swing_assignment.domain

import androidx.paging.PagingData
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ImageRepository {
    suspend fun getSearchImageData(query : String) : Flow<PagingData<ImageDataModel>>
}