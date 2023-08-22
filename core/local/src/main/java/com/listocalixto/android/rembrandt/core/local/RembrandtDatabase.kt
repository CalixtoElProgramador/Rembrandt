package com.listocalixto.android.rembrandt.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.listocalixto.android.rembrandt.core.local.entities.artwork.ArtworkDao
import com.listocalixto.android.rembrandt.core.local.entities.artwork.table.LocalArtwork
import com.listocalixto.android.rembrandt.core.local.entities.manifest.LocalManifest
import com.listocalixto.android.rembrandt.core.local.entities.manifest.ManifestDao
import com.listocalixto.android.rembrandt.core.local.entities.translator.LocalTranslation
import com.listocalixto.android.rembrandt.core.local.entities.translator.TranslatorDao
import com.listocalixto.android.rembrandt.core.local.entities.collection.LocalCollection
import com.listocalixto.android.rembrandt.core.local.entities.collection.CollectionDao
import com.listocalixto.android.rembrandt.core.local.utility.ListConverter
import com.listocalixto.android.rembrandt.core.local.utility.MapConverter
import com.listocalixto.android.rembrandt.core.local.utility.SetConverter

@Database(
    entities = [LocalArtwork::class, LocalManifest::class, LocalTranslation::class, LocalCollection::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(value = [ListConverter::class, MapConverter::class, SetConverter::class])
abstract class RembrandtDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
    abstract fun manifestDao(): ManifestDao
    abstract fun translatorDao(): TranslatorDao
    abstract fun collectionDao(): CollectionDao

    companion object {
        const val NAME = "REMBRANDT_DATABASE"
    }
}
