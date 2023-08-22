package com.listocalixto.android.rembrandt.core.local.entities.collection

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.protobuf.value
import com.listocalixto.android.rembrandt.core.local.utility.SetConverter

@Entity(tableName = "collections")
data class LocalCollection(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("is_private")
    val isPrivate: Boolean,
    @ColumnInfo("principal_image_url")
    val principalImageUrl: String?,
    @ColumnInfo("artwork_ids")
    @TypeConverters(value = [SetConverter::class])
    val artworkIds: Set<Long>,
)
