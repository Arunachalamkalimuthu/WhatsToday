 

package com.whatstoday.dynamicfeatures.characterslist.ui.list.model

import com.whatstoday.dynamicfeatures.characterslist.ui.list.CharactersListFragment

/**
 * Model view to display on the screen [CharactersListFragment].
 */
data class CharacterItem(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)
