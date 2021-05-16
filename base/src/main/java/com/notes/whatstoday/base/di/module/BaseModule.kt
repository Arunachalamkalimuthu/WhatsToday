package com.notes.whatstoday.base.di.module

import com.notes.whatstoday.data.DatabaseService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {

    @Provides
    @Singleton
    fun provideDatabaseService() = DatabaseService()

}