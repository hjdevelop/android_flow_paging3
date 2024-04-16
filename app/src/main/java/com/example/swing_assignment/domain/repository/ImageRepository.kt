package com.example.swing_assignment.domain.repository

import androidx.paging.PagingData
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import dagger.Binds
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Singleton

interface ImageRepository {
    fun getSearchImageData(query : String) : Flow<PagingData<ImageDataModel>>
}