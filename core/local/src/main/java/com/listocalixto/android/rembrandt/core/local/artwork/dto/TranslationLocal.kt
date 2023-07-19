package com.listocalixto.android.rembrandt.core.local.artwork.dto

import androidx.room.ColumnInfo

data class TranslationLocal(
    @ColumnInfo(name = "translation_category")
    val category: String,
    @ColumnInfo(name = "translation_title")
    val title: String,
    @ColumnInfo(name = "translation_content")
    val content: String
)
