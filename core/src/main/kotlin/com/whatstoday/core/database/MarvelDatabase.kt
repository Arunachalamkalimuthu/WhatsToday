package com.whatstoday.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whatstoday.core.database.characterfavorite.CharacterFavorite
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao

/**
 * Marvel room database storing the different requested information like: characters, comics, etc...
 *
 * @see Database
 */
@Database(
    entities = [CharacterFavorite::class],
    exportSchema = false,
    version = 1
)
abstract class MarvelDatabase : RoomDatabase() {

    /**
     * Get character favorite data access object.
     *
     * @return Character favorite dao.
     */
    abstract fun characterFavoriteDao(): CharacterFavoriteDao
}
