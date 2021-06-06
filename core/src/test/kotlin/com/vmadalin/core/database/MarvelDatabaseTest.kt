 

package com.whatstoday.core.database

import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao
import com.whatstoday.libraries.testutils.robolectric.TestRobolectric
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class MarvelDatabaseTest : TestRobolectric() {

    @MockK
    lateinit var marvelDatabase: MarvelDatabase
    @MockK
    lateinit var characterFavoriteDao: CharacterFavoriteDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun obtainCharacterFavoriteDao() {
        every { marvelDatabase.characterFavoriteDao() } returns characterFavoriteDao

        assertThat(
            marvelDatabase.characterFavoriteDao(),
            instanceOf(CharacterFavoriteDao::class.java)
        )
    }
}
