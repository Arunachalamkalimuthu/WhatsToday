 

package com.whatstoday.core.di

import android.content.Context
import com.whatstoday.core.database.MarvelDatabase
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao
import com.whatstoday.core.di.modules.DatabaseModule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DatabaseModuleTest {

    private lateinit var databaseModule: DatabaseModule

    @Before
    fun setUp() {
        databaseModule = DatabaseModule()
    }

    @Test
    fun verifyProvidedMarvelDatabase() {
        val context: Context = mockk()
        val marvelDatabase = databaseModule.provideMarvelDatabase(context)

        assertNotNull(marvelDatabase.characterFavoriteDao())
    }

    @Test
    fun verifyProvidedCharacterFavoriteDao() {
        val marvelDatabase: MarvelDatabase = mockk()
        val characterFavoriteDao: CharacterFavoriteDao = mockk()

        every { marvelDatabase.characterFavoriteDao() } returns characterFavoriteDao

        assertEquals(
            characterFavoriteDao,
            databaseModule.provideCharacterFavoriteDao(marvelDatabase)
        )
        verify { marvelDatabase.characterFavoriteDao() }
    }

    @Test
    fun verifyProvidedCharacterFavoriteRepository() {
        val characterFavoriteDao: CharacterFavoriteDao = mockk()
        val repository = databaseModule.provideCharacterFavoriteRepository(characterFavoriteDao)

        assertEquals(characterFavoriteDao, repository.characterFavoriteDao)
    }
}
