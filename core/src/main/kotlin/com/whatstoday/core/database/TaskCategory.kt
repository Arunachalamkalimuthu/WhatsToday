package com.whatstoday.core.database

import androidx.room.Embedded
import androidx.room.Relation
import com.whatstoday.core.database.category.Category
import com.whatstoday.core.database.task.Task


data class TaskCategory(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category"
    )
    val tasks: List<Task>
)
