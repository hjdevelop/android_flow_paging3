package com.example.swing_assignment.domain.usecase

import com.example.swing_assignment.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchImageDataUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    operator fun invoke(query : String) = imageRepository.getSearchImageData(query).flowOn(Dispatchers.Default)
}