package com.listocalixto.android.rembrandt.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.core.local.artwork.ArtworkDao
import com.listocalixto.android.rembrandt.core.local.artwork.table.LocalArtwork
import com.listocalixto.android.rembrandt.core.local.utility.ListConverter

@Database(entities = [LocalArtwork::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class])
abstract class RembrandtDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao

    companion object {
        const val NAME = "REMBRANDT_DATABASE"
    }
}
