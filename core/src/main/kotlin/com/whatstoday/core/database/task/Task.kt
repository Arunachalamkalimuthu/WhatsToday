package com.whatstoday.core.database.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.whatstoday.core.database.category.Category
import java.util.*

@Entity(
    tableName = "task",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("category"),
        onDelete = ForeignKey.CASCADE
    )]
)
class Task(
    @PrimaryKey val id: Long,
    @ColumnInfo val title: String,
    @ColumnInfo val category:Long,

    @ColumnInfo val description: String,
    @ColumnInfo val state: String,
    @ColumnInfo val expectedDate: Date,
    @ColumnInfo val createdDate: Date,
)
