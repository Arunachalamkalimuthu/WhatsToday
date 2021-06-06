

package com.whatstoday.core.database.characterfavorite

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * The data access object for the [CharacterFavorite] class.
 *
 * @see Dao
 */
@Dao
interface CharacterFavoriteDao {

    /**
     * Obtain all database added favorite characters ordering by name field.
     *
     * @return [LiveData] List with favorite characters.
     */
    @Query("SELECT * FROM character_favorite ORDER BY name")
    fun getAllCharactersFavoriteLiveData(): LiveData<List<CharacterFavorite>>

    /**
     * Obtain all database added favorite characters ordering by name field.
     *
     * @return List with favorite characters.
     */
    @Query("SELECT * FROM character_favorite ORDER BY name")
    suspend fun getAllCharactersFavorite(): List<CharacterFavorite>

    /**
     * Obtain database favorite character by identifier.
     *
     * @param characterFavoriteId Character identifier.
     *
     * @return Favorite character if exist, otherwise null.
     */
    @Query("SELECT * FROM character_favorite WHERE id = :characterFavoriteId")
    suspend fun getCharacterFavorite(characterFavoriteId: Long): CharacterFavorite?

    /**
     * Delete all database favorite characters.
     */
    @Query("DELETE FROM character_favorite")
    suspend fun deleteAllCharactersFavorite()

    /**
     * Delete database favorite character by identifier.
     *
     * @param characterFavoriteId Character identifier.
     */
    @Query("DELETE FROM character_favorite WHERE id = :characterFavoriteId")
    suspend fun deleteCharacterFavoriteById(characterFavoriteId: Long)

    /**
     * Delete database favorite character.
     *
     * @param character Character favorite.
     */
    @Delete
    suspend fun deleteCharacterFavorite(character: CharacterFavorite)

    /**
     * Add to database a list of favorite characters.
     *
     * @param characters List of favorite characters.
     */
    @Insert
    suspend fun insertCharactersFavorites(characters: List<CharacterFavorite>)

    /**
     * Add to database a favorite character.
     *
     * @param character Favorite character.
     */
    @Insert
    suspend fun insertCharacterFavorite(character: CharacterFavorite)
}
