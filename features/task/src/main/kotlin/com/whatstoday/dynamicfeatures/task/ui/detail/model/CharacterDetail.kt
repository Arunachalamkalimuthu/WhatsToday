

package com.whatstoday.dynamicfeatures.task.ui.detail.model

import com.whatstoday.dynamicfeatures.task.ui.detail.TaskDetailFragment

/**
 * Model view to display on the screen [TaskDetailFragment].
 */
data class CharacterDetail(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)
