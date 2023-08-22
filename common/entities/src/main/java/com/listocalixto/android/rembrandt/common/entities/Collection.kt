package com.listocalixto.android.rembrandt.common.entities

data class Collection(
    val id: String,
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val principalImageUrl: String?,
    val artworkIds: Set<Long>,
)
