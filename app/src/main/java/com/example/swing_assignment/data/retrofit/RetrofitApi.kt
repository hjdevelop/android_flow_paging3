package com.example.swing_assignment.data.retrofit

import com.example.swing_assignment.data.model.RetrofitDataModel
import com.example.swing_assignment.data.retrofit.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("/search/photos?client_id=${API_KEY}")
    suspend fun getSearchImage(
        @Query("query")
        query : String,
        @Query("page")
        page : Int,
        @Query("per_page")
        perPage : Int,
        @Query("order_by")
        orderBy : String,
        @Query("content_filter")
        contentFilter : String
    ) : RetrofitDataModel
}