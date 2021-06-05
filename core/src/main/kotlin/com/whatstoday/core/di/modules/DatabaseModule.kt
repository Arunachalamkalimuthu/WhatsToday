

package com.whatstoday.core.di.modules

import android.content.Context
import androidx.room.Room
import com.whatstoday.core.BuildConfig
import com.whatstoday.core.database.MarvelDatabase
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteRepository
import com.whatstoday.core.database.migrations.MIGRATION_1_2
import com.whatstoday.core.di.CoreComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [MarvelDatabase].
     *
     * @return Instance of marvel database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "whatstoday"
        ).addMigrations(MIGRATION_1_2)
            .build()

    /**
     * Create a provider method binding for [CharacterFavoriteDao].
     *
     * @return Instance of character favorite data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteDao(marvelDatabase: MarvelDatabase) =
        marvelDatabase.characterFavoriteDao()

    /**
     * Create a provider method binding for [CharacterFavoriteRepository].
     *
     * @return Instance of character favorite repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteRepository(
        characterFavoriteDao: CharacterFavoriteDao
    ) = CharacterFavoriteRepository(characterFavoriteDao)
}
