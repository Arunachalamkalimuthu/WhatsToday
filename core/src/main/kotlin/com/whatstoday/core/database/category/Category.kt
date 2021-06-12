package com.whatstoday.core.database.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "category")
class Category(
    @PrimaryKey val id: Long,
    @ColumnInfo
    val indicator: String,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val state: String,
    @ColumnInfo val excectedDate: Date,
    @ColumnInfo val createdDate: Date,
)
