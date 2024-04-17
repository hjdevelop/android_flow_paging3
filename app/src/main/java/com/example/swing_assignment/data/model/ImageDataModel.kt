package com.example.swing_assignment.data.model

//api 데이터를 받아오고 북마크 기능을 위해 boolean 속성을 추가한 데이터 클래스 입니다.
data class ImageDataModel (
    val result : RetrofitDataModel.Result,
    var isLiked: Boolean
)