package co.sharif.namava.data.model

import com.squareup.moshi.Json


data class AuthModel (
    val accessToken: String,
    val tokenType: String,
    val scope: String
)

data class VideoModel (
    val total: Int,
    val page: Int,
    val per_page: Int,
    val paging: Paging,
    @Json(name = "data")
    val resultData: List<Datum>
)

