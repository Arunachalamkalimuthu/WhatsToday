 

package com.whatstoday.core.network.responses

import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class DataResponseTest {

    @Test
    fun createDataResponse_ShouldAddCorrectAttributes() {
        val offset = 10
        val limit = 50
        val total = 1000
        val count = 50
        val results: List<String> = mockk()

        val dataResponse = DataResponse(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            results = results
        )

        Assert.assertEquals(offset, dataResponse.offset)
        Assert.assertEquals(limit, dataResponse.limit)
        Assert.assertEquals(total, dataResponse.total)
        Assert.assertEquals(count, dataResponse.count)
        Assert.assertEquals(results, dataResponse.results)
    }
}
