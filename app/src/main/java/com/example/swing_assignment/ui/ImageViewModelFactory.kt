package com.example.swing_assignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swing_assignment.data.repository.ImageRepositoryImpl
import com.example.swing_assignment.data.retrofit.RetrofitClient
import com.example.swing_assignment.data.retrofit.RetrofitInterface
import com.example.swing_assignment.domain.ImageRepository

class ImageViewModelFactory() : ViewModelProvider.Factory {
    private val imageRepository = ImageRepositoryImpl(RetrofitClient.api)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(imageRepository) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}