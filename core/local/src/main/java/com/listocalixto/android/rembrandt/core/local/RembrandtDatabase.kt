package com.listocalixto.android.rembrandt.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.core.local.artwork.ArtworkDao
import com.listocalixto.android.rembrandt.core.local.artwork.table.LocalArtwork
import com.listocalixto.android.rembrandt.core.local.manifest.LocalManifest
import com.listocalixto.android.rembrandt.core.local.manifest.ManifestDao
import com.listocalixto.android.rembrandt.core.local.translator.LocalTranslation
import com.listocalixto.android.rembrandt.core.local.translator.TranslatorDao
import com.listocalixto.android.rembrandt.core.local.utility.ListConverter
import com.listocalixto.android.rembrandt.core.local.utility.MapConverter

@Database(
    entities = [LocalArtwork::class, LocalManifest::class, LocalTranslation::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(value = [ListConverter::class, MapConverter::class])
abstract class RembrandtDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
    abstract fun manifestDao(): ManifestDao
    abstract fun translatorDao(): TranslatorDao

    companion object {
        const val NAME = "REMBRANDT_DATABASE"
    }
}
