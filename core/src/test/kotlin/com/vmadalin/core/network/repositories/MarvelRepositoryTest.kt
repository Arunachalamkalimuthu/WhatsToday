 

package com.whatstoday.core.network.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.whatstoday.core.BuildConfig
import com.whatstoday.core.network.repositiories.MarvelRepository
import com.whatstoday.core.network.services.MarvelService
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val API_PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC

class MarvelRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var marvelService: MarvelService
    private lateinit var marvelRepository: MarvelRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        marvelRepository = MarvelRepository(marvelService)
    }

    @Test
    fun getCharacters() = runBlocking {
        val charactersOffset = 0
        val charactersLimit = 20
        val apiKey = slot<String>()
        val hash = slot<String>()
        val timestamp = slot<String>()
        val offset = slot<Int>()
        val limit = slot<Int>()

        marvelRepository.getCharacters(
            offset = charactersOffset,
            limit = charactersLimit
        )

        coVerify {
            marvelService.getCharacters(
                apiKey = capture(apiKey),
                hash = capture(hash),
                timestamp = capture(timestamp),
                offset = capture(offset),
                limit = capture(limit)
            )
        }

        assertEquals(API_PUBLIC_KEY, apiKey.captured)
        assertEquals(charactersOffset, offset.captured)
        assertEquals(charactersLimit, limit.captured)
        assertNotNull(hash.captured)
        assertNotNull(timestamp.captured)
    }

    @Test
    fun getCharacter() = runBlocking {
        val characterId = 3L
        val id = slot<Long>()
        val apiKey = slot<String>()
        val hash = slot<String>()
        val timestamp = slot<String>()

        marvelRepository.getCharacter(characterId)

        coVerify {
            marvelService.getCharacter(
                id = capture(id),
                apiKey = capture(apiKey),
                hash = capture(hash),
                timestamp = capture(timestamp)
            )
        }

        assertEquals(characterId, id.captured)
        assertEquals(API_PUBLIC_KEY, apiKey.captured)
        assertNotNull(hash.captured)
        assertNotNull(timestamp.captured)
    }
}
