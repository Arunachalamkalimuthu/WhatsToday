

package com.whatstoday.dynamicfeatures.characterslist.ui.detail.model

import com.whatstoday.core.network.responses.BaseResponse
import com.whatstoday.core.network.responses.CharacterResponse
import com.whatstoday.core.network.responses.CharacterThumbnailResponse
import com.whatstoday.core.network.responses.DataResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterDetailMapperTest {

    private val mapper = CharacterDetailMapper()

    @Test(expected = NoSuchElementException::class)
    fun characterMapper_WithEmptyResults_ShouldThrowException() {
        runBlocking {
            val response = BaseResponse(
                code = 200,
                status = "Ok",
                message = "Ok",
                data = DataResponse<CharacterResponse>(
                    offset = 0,
                    limit = 0,
                    total = 0,
                    count = 0,
                    results = emptyList()
                )
            )

            mapper.map(response)
        }
    }

    @Test
    fun characterMapper_WithResults_ShouldParseModel() = runBlocking {
        val response = BaseResponse(
            code = 200,
            status = "Ok",
            message = "Ok",
            data = DataResponse(
                offset = 0,
                limit = 0,
                total = 1,
                count = 1,
                results = listOf(
                    CharacterResponse(
                        id = 1011334,
                        name = "3-D Man",
                        description = "",
                        thumbnail = CharacterThumbnailResponse(
                            path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                            extension = "jpg"
                        )
                    )
                )
            )
        )

        mapper.map(response).run {
            assertEquals(1011334, this.id)
            assertEquals("3-D Man", this.name)
            assertEquals("", this.description)
            assertEquals(
                "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
                this.imageUrl
            )
        }
    }
}
