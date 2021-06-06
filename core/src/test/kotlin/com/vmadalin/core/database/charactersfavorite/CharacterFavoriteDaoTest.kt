

package com.whatstoday.core.database.charactersfavorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.whatstoday.core.database.MarvelDatabase
import com.whatstoday.core.database.characterfavorite.CharacterFavorite
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao
import com.whatstoday.libraries.testutils.livedata.getValue
import com.whatstoday.libraries.testutils.robolectric.TestRobolectric
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterFavoriteDaoTest : TestRobolectric() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MarvelDatabase
    private lateinit var characterFavoriteDao: CharacterFavoriteDao
    private val fakeCharactersFavorite = listOf(
        CharacterFavorite(0, "3-D Man", "http://i.annihil.us/535fecbbb9784.jpg"),
        CharacterFavorite(1, "A-Bomb (HAS)", "http://i.annihil.us/5232158de5b16.jpg"),
        CharacterFavorite(2, "A.I.M", "http://i.annihil.us/52602f21f29ec.jpg")
    )

    @Before
    fun setUp() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room
            .inMemoryDatabaseBuilder(context, MarvelDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        characterFavoriteDao = database.characterFavoriteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun obtainAllCharactersFavoriteLiveData_WithoutData_ShouldReturnNull() {
        val characters = characterFavoriteDao.getAllCharactersFavoriteLiveData()
        assertTrue(getValue(characters).isNullOrEmpty())
    }

    @Test
    fun obtainAllCharactersFavoriteLiveData_WithData_ShouldReturnSorted() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)
        val characters = characterFavoriteDao.getAllCharactersFavoriteLiveData()

        assertEquals(fakeCharactersFavorite, getValue(characters))
    }

    @Test
    fun obtainAllCharactersFavorite_WithoutData_ShouldReturnEmpty() = runBlocking {
        assertTrue(characterFavoriteDao.getAllCharactersFavorite().isNullOrEmpty())
    }

    @Test
    fun obtainAllCharactersFavorite_WithData_ShouldReturnSorted() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)

        assertEquals(fakeCharactersFavorite, characterFavoriteDao.getAllCharactersFavorite())
    }

    @Test
    fun obtainCharacterFavoriteById_WithoutData_ShouldNotFound() = runBlocking {
        val characterToFind = fakeCharactersFavorite.first()

        assertNull(characterFavoriteDao.getCharacterFavorite(characterToFind.id))
    }

    @Test
    fun obtainCharacterFavoriteById_WithData_ShouldFound() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)

        val characterToFind = fakeCharactersFavorite.first()
        assertEquals(characterToFind, characterFavoriteDao.getCharacterFavorite(characterToFind.id))
    }

    @Test
    fun insertCharacterFavorite_ShouldAdd() = runBlocking {
        fakeCharactersFavorite.forEach {
            characterFavoriteDao.insertCharacterFavorite(it)
        }

        assertEquals(fakeCharactersFavorite, characterFavoriteDao.getAllCharactersFavorite())
    }

    @Test
    fun deleteAllCharactersFavorite_ShouldRemoveAll() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)
        characterFavoriteDao.deleteAllCharactersFavorite()

        assertTrue(characterFavoriteDao.getAllCharactersFavorite().isNullOrEmpty())
    }

    @Test
    fun deleteCharacterFavorite_Stored_ShouldRemoveIt() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)

        val characterToRemove = fakeCharactersFavorite.first()
        characterFavoriteDao.deleteCharacterFavorite(characterToRemove)

        assertThat(characterFavoriteDao.getAllCharactersFavorite(), not(hasItem(characterToRemove)))
    }

    @Test
    fun deleteCharacterFavorite_NoStored_ShouldNotRemoveNothing() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)

        val characterToRemove = CharacterFavorite(5, "test", "url")
        characterFavoriteDao.deleteCharacterFavorite(characterToRemove)

        assertEquals(fakeCharactersFavorite, characterFavoriteDao.getAllCharactersFavorite())
    }

    @Test
    fun deleteCharacterFavoriteById_Stored_ShouldRemoveIt() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)

        val characterToRemove = fakeCharactersFavorite.first()
        characterFavoriteDao.deleteCharacterFavoriteById(characterToRemove.id)

        assertThat(characterFavoriteDao.getAllCharactersFavorite(), not(hasItem(characterToRemove)))
    }

    @Test
    fun deleteCharacterFavoriteById_NoStored_ShouldNotRemoveNothing() = runBlocking {
        characterFavoriteDao.insertCharactersFavorites(fakeCharactersFavorite)

        val characterNoStoredId = 100L
        characterFavoriteDao.deleteCharacterFavoriteById(characterNoStoredId)

        assertEquals(fakeCharactersFavorite, characterFavoriteDao.getAllCharactersFavorite())
    }
}
