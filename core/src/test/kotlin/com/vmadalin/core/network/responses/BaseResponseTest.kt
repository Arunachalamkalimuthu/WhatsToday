 

package com.whatstoday.core.network.responses

import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class BaseResponseTest {

    @Test
    fun createBaseResponse_ShouldAddCorrectAttributes() {
        val code = 200
        val status = "Ok"
        val message = "Ok"
        val data: DataResponse<String> = mockk()

        val baseResponse = BaseResponse(
            code = code,
            status = status,
            message = message,
            data = data
        )

        Assert.assertEquals(code, baseResponse.code)
        Assert.assertEquals(status, baseResponse.status)
        Assert.assertEquals(message, baseResponse.message)
        Assert.assertEquals(data, baseResponse.data)
    }
}
