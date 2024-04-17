package com.example.swing_assignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.swing_assignment.data.model.BookmarkDataModel
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.domain.usecase.SearchImageDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

//View와 Model의 중간 매개체를 담당하는 ViewModel 입니다.
@HiltViewModel
class ImageViewModel @Inject constructor(private val searchImageDataUseCase: SearchImageDataUseCase) : ViewModel() {
    private val _imageList = MutableStateFlow<PagingData<ImageDataModel>>(PagingData.empty())
    val imageList = _imageList.asStateFlow()

    private val _bookmarkList: MutableLiveData<List<BookmarkDataModel>> = MutableLiveData()
    val bookmarkList: LiveData<List<BookmarkDataModel>> get() = _bookmarkList

    fun getImageList(query : String) {
        viewModelScope.launch {
            searchImageDataUseCase(query)
                .cachedIn(viewModelScope)
                .collectLatest { image ->
                    _imageList.emit(image)
                }
        }
    }

    fun addBookmark(item : ImageDataModel) {
        viewModelScope.launch {
            val currentList = _bookmarkList.value?.toMutableList() ?: mutableListOf()
            currentList.add(BookmarkDataModel(item.result.id, item.result.urls.regular))

            val updateData = _imageList.value.map { image ->
                if (image.result.id == item.result.id) {
                    image.copy(isLiked = !image.isLiked)
                }
                else image
            }
            _imageList.emit(updateData)

            _bookmarkList.value = currentList
        }
    }

    fun deleteBookmarkForFeed(item : ImageDataModel) {
        viewModelScope.launch {
            val currentList = _bookmarkList.value?.toMutableList() ?: mutableListOf()
            currentList.removeIf { it.id == item.result.id }

            val updateData = _imageList.value.map { image ->
                if (image.result.id == item.result.id) {
                    image.copy(isLiked = !image.isLiked)
                }
                else image
            }
            _imageList.emit(updateData)

            _bookmarkList.value = currentList
        }

    }

    fun deleteBookmarkForBookmark(item : BookmarkDataModel) {
        viewModelScope.launch{
            val currentList = _bookmarkList.value?.toMutableList() ?: mutableListOf()
            currentList.removeIf { it.id == item.id }

            val updateData = _imageList.value.map { image ->
                if (image.result.id == item.id) {
                    image.copy(isLiked = !image.isLiked)
                }
                else image
            }
            _imageList.emit(updateData)

            _bookmarkList.value = currentList
        }

    }
}