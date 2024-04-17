package com.example.swing_assignment.domain.repository

import androidx.paging.PagingData
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import dagger.Binds
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Singleton

//비즈니스 로직을 분리하기 위한 Repository 입니다.
interface ImageRepository {
    fun getSearchImageData(query : String) : Flow<PagingData<ImageDataModel>>
}