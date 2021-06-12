package com.whatstoday.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.whatstoday.core.database.category.Category
import com.whatstoday.core.database.category.CategoryDao
import com.whatstoday.core.database.characterfavorite.CharacterFavorite
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao
import com.whatstoday.core.database.convertors.Converters
import com.whatstoday.core.database.task.Task
import com.whatstoday.core.database.task.TaskDao

/**
 * Marvel room database storing the different requested information like: characters, comics, etc...
 *
 * @see Database
 */
@Database(
    entities = [CharacterFavorite::class, Task::class, Category::class],

    exportSchema = false,
    version = 3
)
@TypeConverters(value = [Converters::class])

abstract class WhatsTodayDatabase : RoomDatabase() {

    /**
     * Get character favorite data access object.
     *
     * @return Character favorite dao.
     */
    abstract fun characterFavoriteDao(): CharacterFavoriteDao


    abstract fun taskDao(): TaskDao

    abstract fun categoryDao(): CategoryDao
}
