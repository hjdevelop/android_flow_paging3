package com.example.swing_assignment.domain.usecase

import com.example.swing_assignment.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//비즈니스 로직을 처리하기 위한 UseCase 입니다.
class SearchImageDataUseCase @Inject constructor(private val imageRepository: ImageRepository) {
    operator fun invoke(query : String) = imageRepository.getSearchImageData(query).flowOn(Dispatchers.Default)
}