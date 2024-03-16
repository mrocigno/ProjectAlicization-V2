package br.com.mrocigno.alicization.remote.model.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("categories") val categories: List<CategoryResponse>
)

data class CategoryResponse(
    @SerializedName("type") val type: String,
    @SerializedName("items") val items: List<MangaResponse>
)

data class MangaResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("payload") val payload: PayloadResponse,
    @SerializedName("es_score") val esScore: Double
)

data class PayloadResponse(
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("start_year") val startYear: Int,
    @SerializedName("published") val published: String,
    @SerializedName("score") val score: String,
    @SerializedName("status") val status: String
)