package com.whatstoday.core.network.repositiories

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.whatstoday.core.extensions.toMD5
import com.whatstoday.core.network.responses.BaseResponse
import com.whatstoday.core.network.responses.CharacterResponse
import com.whatstoday.core.network.services.TaskService


/**
 * Repository module for handling marvel API network operations [MarvelService].
 */


class TaskRepository(
    @VisibleForTesting(otherwise = PRIVATE)
    internal val service: TaskService
) {
    companion object {
        const val API_PUBLIC_KEY = " "
        const val API_PRIVATE_KEY = " "
        const val HASH_FORMAT = "%s%s%s"

    }

    /**
     * Get all info of Marvel character.
     *
     * @param id A single character id.
     * @return Response for single character resource.
     */
    suspend fun getCharacter(id: Long): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getCharacter(
            id = id,
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp
        )
    }

    /**
     * Get all Marvel characters by paged.
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set to the specified number of resources.
     * @return Response for comic characters resource.
     */
    suspend fun getCharacters(offset: Int, limit: Int): BaseResponse<CharacterResponse> {
        val timestamp = System.currentTimeMillis().toString()
        return service.getCharacters(
            apiKey = API_PUBLIC_KEY,
            hash = generateApiHash(timestamp),
            timestamp = timestamp,
            offset = offset,
            limit = limit
        )
    }

    // ============================================================================================
    //  Private generators methods
    // ============================================================================================

    /**
     * Generate a md5 digest of the timestamp parameter, private API key and public.
     *
     * @param timestamp A digital current record of the time.
     * @return The MD5 Hash
     */
    private fun generateApiHash(timestamp: String) =
        HASH_FORMAT.format(timestamp, API_PRIVATE_KEY, API_PUBLIC_KEY).toMD5()
}
