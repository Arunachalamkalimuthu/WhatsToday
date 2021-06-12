package com.whatstoday.core.di.modules

import android.content.Context
import androidx.room.Room
import com.whatstoday.core.database.WhatsTodayDatabase
import com.whatstoday.core.database.category.CategoryDao
import com.whatstoday.core.database.category.CategoryRespository
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteDao
import com.whatstoday.core.database.characterfavorite.CharacterFavoriteRepository
import com.whatstoday.core.database.migrations.MIGRATION_1_2
import com.whatstoday.core.database.task.TaskDao
import com.whatstoday.core.database.task.TaskRepository
import com.whatstoday.core.di.CoreComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [WhatsTodayDatabase].
     *
     * @return Instance of marvel database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            WhatsTodayDatabase::class.java,
            "whatstoday"
        ).addMigrations(MIGRATION_1_2)
            .build()

    /**
     * Create a provider method binding for [CharacterFavoriteDao].
     *
     * @return Instance of character favorite data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteDao(marvelDatabase: WhatsTodayDatabase) =
        marvelDatabase.characterFavoriteDao()


    @Singleton
    @Provides
    fun provideTaskDao(marvelDatabase: WhatsTodayDatabase) =
        marvelDatabase.taskDao()

    @Singleton
    @Provides
    fun provideCategoryDao(marvelDatabase: WhatsTodayDatabase) =
        marvelDatabase.categoryDao()

    /**
     * Create a provider method binding for [CharacterFavoriteRepository].
     *
     * @return Instance of character favorite repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteRepository(
        characterFavoriteDao: CharacterFavoriteDao
    ) = CharacterFavoriteRepository(characterFavoriteDao)


    @Singleton
    @Provides
    fun provideTaskRepostiroy(
        taskDao: TaskDao
    ) = TaskRepository(taskDao)


    @Singleton
    @Provides
    fun provideCategoryRespository(
        categoryDao: CategoryDao
    ) = CategoryRespository(categoryDao)
}
