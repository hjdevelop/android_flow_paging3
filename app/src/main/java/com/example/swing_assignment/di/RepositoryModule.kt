package com.example.swing_assignment.di

import com.example.swing_assignment.data.repository.ImageRepositoryImpl
import com.example.swing_assignment.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun imageRepository(imageRepositoryImpl: ImageRepositoryImpl) : ImageRepository
}