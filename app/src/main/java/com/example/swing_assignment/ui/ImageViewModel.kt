package com.example.swing_assignment.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.swing_assignment.data.model.BookmarkDataModel
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.model.RetrofitDataModel
import com.example.swing_assignment.data.repository.ImageRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
        val currentList = _bookmarkList.value?.toMutableList() ?: mutableListOf()
        currentList.add(BookmarkDataModel(item.result.id, item.result.urls.regular))

        for (i in currentList) {
            Log.d("bookmark", i.toString())
        }
        _bookmarkList.value = currentList
    }

    fun deleteBookmark(item : ImageDataModel) {
        val currentList = _bookmarkList.value?.toMutableList() ?: mutableListOf()

        currentList.removeIf { it.id == item.result.id }

        _bookmarkList.value = currentList
    }

    fun deleteBookmark2(item : BookmarkDataModel) {
        val currentList = _bookmarkList.value?.toMutableList() ?: mutableListOf()

        currentList.removeIf { it.id == item.id }

        _bookmarkList.value = currentList
    }
}