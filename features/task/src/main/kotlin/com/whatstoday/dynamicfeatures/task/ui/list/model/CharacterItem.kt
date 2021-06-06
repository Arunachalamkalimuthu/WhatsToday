 

package com.whatstoday.dynamicfeatures.task.ui.list.model

import com.whatstoday.dynamicfeatures.task.ui.list.TaskListFragment

/**
 * Model view to display on the screen [TaskListFragment].
 */
data class CharacterItem(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)
