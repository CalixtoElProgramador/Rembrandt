package com.listocalixto.android.rembrandt.data.source.remote.implementation.response.main


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse(
    @SerialName("current_page")
    val currentPage: Int?,
    @SerialName("limit")
    val limit: Int?,
    @SerialName("offset")
    val offset: Int?,
    @SerialName("total")
    val total: Int?,
    @SerialName("total_pages")
    val totalPages: Int?
)