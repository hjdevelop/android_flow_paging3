package com.example.swing_assignment.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.swing_assignment.data.model.ImageDataModel
import com.example.swing_assignment.data.retrofit.RetrofitApi
import java.lang.Exception

class ImagePagingSource(private val api : RetrofitApi, private val query : String) : PagingSource<Int, ImageDataModel>() {
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ImageDataModel>): Int? {
        // 페이지의 이전 키(또는 이전이 null 인경우 다음 키)를 가져와야 합니다.
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDataModel> {
        val position = params.key ?: FIRST_PAGE_INDEX
        return try {
            val pictures = api.getSearchImage(query, position, 30, "relevant", "low").results.map { ImageDataModel(it, false) }

            LoadResult.Page(
                data = pictures,
                prevKey = if (position == FIRST_PAGE_INDEX) null else position - 1,
                nextKey = if (pictures.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}