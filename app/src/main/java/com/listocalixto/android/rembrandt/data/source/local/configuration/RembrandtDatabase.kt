package com.listocalixto.android.rembrandt.data.source.local.configuration

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ArtworkTable
import com.listocalixto.android.rembrandt.data.source.local.implementation.table.main.ManifestTable

@Database(entities = [ArtworkTable::class, ManifestTable::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class])
abstract class RembrandtDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
    abstract fun manifestDao(): ManifestDao

    companion object {
        const val NAME = "REMBRANDT_DATABASE"
    }
}
