package com.example.swing_assignment.di

import com.example.swing_assignment.data.retrofit.Constants.Companion.BASE_URL
import com.example.swing_assignment.data.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//의존성 주입을 위해 모듈화 했습니다.
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit) : RetrofitApi {
        return retrofit.create(RetrofitApi::class.java)
    }
}