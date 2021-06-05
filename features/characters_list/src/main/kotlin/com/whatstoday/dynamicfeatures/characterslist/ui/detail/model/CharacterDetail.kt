

package com.whatstoday.dynamicfeatures.characterslist.ui.detail.model

import com.whatstoday.dynamicfeatures.characterslist.ui.detail.CharacterDetailFragment

/**
 * Model view to display on the screen [CharacterDetailFragment].
 */
data class CharacterDetail(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)
