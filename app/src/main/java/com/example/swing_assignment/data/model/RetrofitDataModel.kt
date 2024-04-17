package com.example.swing_assignment.data.model

import com.google.gson.annotations.SerializedName

//네트워크 통신으로 받아올 데이터를 위한 데이터 클래스 입니다.
data class RetrofitDataModel (
    val results: List<Result>,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    data class Result(
        @SerializedName("blur_hash")
        val blurHash: String,
        val color: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("current_user_collections")
        val currentUserCollections: List<Any>,
        val description: String,
        val height: Int,
        val id: String,
        @SerializedName("liked_by_user")
        val likedByUser: Boolean,
        val likes: Int,
        val links: Links,
        val urls: Urls,
        val user: User,
        val width: Int
    )

    data class User(
        @SerializedName("first_name")
        val firstName: String,
        val id: String,
        @SerializedName("instagram_username")
        val instagramUsername: String,
        @SerializedName("last_name")
        val lastName: String,
        val links: LinksX,
        val name: String,
        @SerializedName("portfolio_url")
        val portfolioUrl: String,
        @SerializedName("profile_image")
        val profileImage: ProfileImage,
        @SerializedName("twitter_username")
        val twitterUsername: String,
        val username: String
    )

    data class LinksX(
        val html: String,
        val likes: String,
        val photos: String,
        val self: String
    )

    data class ProfileImage(
        val large: String,
        val medium: String,
        val small: String
    )

    data class Urls(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
    )

    data class Links(
        val download: String,
        val html: String,
        val self: String
    )
}