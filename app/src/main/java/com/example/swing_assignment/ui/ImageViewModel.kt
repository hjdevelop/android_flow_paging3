package com.example.swing_assignment.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.swing_assignment.data.model.BookmarkDataModel
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import com.example.swing_assignment.data.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ImageViewModel(private val imageRepository : ImageRepositoryImpl) : ViewModel() {
    private val _imageList = MutableStateFlow<PagingData<ImageDataModel>>(PagingData.empty())
    val imageList = _imageList.asStateFlow()

    private val _bookmarkList: MutableLiveData<List<BookmarkDataModel>> = MutableLiveData()
    val bookmarkList: LiveData<List<BookmarkDataModel>> get() = _bookmarkList

    fun getImageList(query : String) {
        viewModelScope.launch {
            imageRepository.getSearchImageData(query)
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